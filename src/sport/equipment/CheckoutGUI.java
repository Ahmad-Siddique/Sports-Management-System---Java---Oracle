/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sport.equipment;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author HERO
 */
public class CheckoutGUI extends JFrame implements Serializable {

    JLabel l1, l2, l3, l4, l5, l6;
    JButton b1, b2, b3, b4, b5, b6;
    JTextField t1, t2;
    int oo;
    Customer name2;
    String name = "";
    String bill = "";
    int totalbill = 0;
    Equipment obj1;

    ArrayList<Integer> gg1 = new ArrayList<Integer>();

    CheckoutGUI(String naem, Equipment obj) {
        obj1 = obj;
        name = naem;
        setLayout(new BorderLayout());
        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        ArrayList<Customer_Order> o = Customer_Order.readFromFile();
//          int count=0;
//          int total=0;
//          String ss="";
        int count = 0;
        int total = 0;
        String ss = "";

        ss += "========================= BILL ========================\n";
        ss += "Name: " + name + "\n";
        for (int i = 0; i < Equipment.gg.size(); i++) {

            ss += "======= EQUIPMENT " + (count + 1) + " ========="
                    + "\nEQUIPMENT: " + Equipment.gg.get(i).getEquipment_Name() + "\n"
                    + "Price: " + Equipment.gg.get(i).getprice() + "\n";
            total += Equipment.gg.get(i).getprice();
            count++;
            System.out.println(total);

        }
//          
//          
//           ss+="========================= BILL ========================\n";
//          ss+="Name: "+name+"\n";
//          for (int i = 0; i < Equipment.gg.size(); i++) {
//              
//            
//                ss+="======= EQUIPMENT "+(count+1)+" ========="+
//                "\nEQUIPMENT: "+Equipment.gg.get(i).getEquipment_Name()+"\n"+
//                "Price: "+Equipment.gg.get(i).getprice()+"\n";
//                total+=Equipment.gg.get(i).getprice();
//                count++;
//                System.out.println(total);
//               
//                
//                
//                
//                
//            }
//          ss=ss+"\nTotal Cost: "+total+"\nTotal Cost with GST: "+(total+total*0.2);
//        
//          
//          
//          
//          
//          
//          
//          
//          
//          
//          
////         ss+="========================= BILL ========================\n";
////          ss+="Name: "+name+"\n";
////          for (int i = 0; i < o.size(); i++) {
////              System.out.println(o.get(i).getCustomer_CNIC().getCustomer_Name());
////            if(o.get(i).getCustomer_CNIC().getCustomer_Name().toUpperCase().equals(name.toUpperCase())){
////                ss+="======= EQUIPMENT "+(count+1)+" ========="+
////                "EQUIPMENT: "+o.get(i).getOrder().getEquipment_Name()+"\n"+
////                "Price: "+o.get(i).getOrder().getprice()+"\n";
////                total+=o.get(i).getOrder().getprice();
////                count++;
////                System.out.println(total);
////                
////            }
////        }
//          System.out.println(name);
        bill = ss;
        totalbill = total;
        l1 = new JLabel("WELCOME TO CATEOGRY MENU");
        b1 = new JButton("Return to Previous Menu");

        b4 = new JButton("Check Bill");

        l6 = new JLabel("Delivery Method (Courier / Pickup): ");
        t2 = new JTextField(30);
        b5 = new JButton("CONFIRM ORDER");
        l3 = new JLabel("Apply Voucher ID (Optional): ");
        t1 = new JTextField(30);
        b2 = new JButton("Add Voucher");

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1));
        p1.add(l1);

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2, 2));
        p.add(l6);
        p.add(t2);
        p.add(l3);
        p.add(t1);

        JPanel p3 = new JPanel();
        p3.setLayout(new GridLayout(4, 1));
        p3.add(b2);
        p3.add(b5);

        p3.add(b4);
        p3.add(b1);

        add(p1, BorderLayout.NORTH);
        add(p, BorderLayout.CENTER);

        add(p3, BorderLayout.SOUTH);

        MyActionListener a = new MyActionListener();
        b1.addActionListener(a);
        b2.addActionListener(a);

        b4.addActionListener(a);
        b5.addActionListener(a);

    }

    private class MyActionListener implements ActionListener {

        public MyActionListener() {
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getActionCommand().equals("Return to Previous Menu")) {
                dispose();
                Customer_Equipment_Order_CategoryGUI obj = new Customer_Equipment_Order_CategoryGUI(name2, obj1);
            } else if (ae.getActionCommand().equals(b2.getText())) {
                // print dishes

                try {

                    Class.forName("oracle.jdbc.driver.OracleDriver");

                    Connection con = DriverManager.getConnection(
                            "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=sportsdb)))", "sports", "sports");

                    Statement mystmt = con.createStatement();
                    ResultSet results = mystmt.executeQuery("Select * from VOUCHER");

                    while (results.next()) {
                        if (Integer.parseInt(t1.getText()) == results.getInt(1)) {
                            totalbill = totalbill - results.getInt(3);
                            JOptionPane.showMessageDialog(new JFrame(), "Voucher Applied on Bill... Kindly Check Ur bill again");
                            gg1.add(results.getInt(1));
                            break;

                        }
                    }

                    System.out.println(totalbill);

                } catch (Exception e) {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(new JFrame(), "Invalid Voucher");
                }

//                  if(t1.getText().toUpperCase().equals("arshik".toUpperCase()) || t1.getText().toUpperCase().equals("ahmad".toUpperCase())){
//                  System.out.println("You have been given disocunt 10%");
//                  totalbill=totalbill-totalbill*0.1;
//                  JOptionPane.showMessageDialog(new JFrame(), "You have been given discount");
//                  JOptionPane.showMessageDialog(new JFrame(),"Your total after applying voucher is: " + totalbill);
//                  JOptionPane.showMessageDialog(new JFrame(),"Your total after applying voucher and GST is: "+(totalbill+(totalbill*0.2)));
            } else if (ae.getActionCommand().equals(b5.getText())) {

                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");

                    Connection con = DriverManager.getConnection(
                            "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=sportsdb)))", "sports", "sports");
                    String gg = "";

                    System.out.println("exec order");
                    Statement mystmt = con.createStatement();
                    int i = 1;
                    ResultSet results1 = mystmt.executeQuery("Select * from ORDER_CUS");
                    while (results1.next()) {
                        i += 1;
                    }

                    PreparedStatement prepareStatement = con.prepareStatement("INSERT INTO ORDER_CUS" + " VALUES (?, ?, ?, ?, ?)");
                    System.out.println("exec order2");
                    prepareStatement.setInt(1, i);
                    prepareStatement.setString(2, java.time.LocalDate.now().toString());
                    prepareStatement.setInt(3, totalbill);
                    prepareStatement.setString(4, t2.getText());
                    prepareStatement.setString(5, bill);
                    System.out.println("exec order3");
                    //                    prepareStatement.setString(6,t5.getText());
                    prepareStatement.execute();
                    System.out.println("Order key: "+i);
                    System.out.println("DATA INSERTED Into ORDER_CUS");

                    PreparedStatement prepareStatement1 = con.prepareStatement("INSERT INTO CUSTOMER_ORDER" + " VALUES (?, ?)");
                    prepareStatement1.setInt(1,  Customer_Login.login_val);
                    prepareStatement1.setInt(2,i);
                    prepareStatement1.execute();

                    System.out.println("DATA INSERTED Into CUSTOMER ORDER");
                    
                    
                    
                    
                    for (int j = 0; j < gg1.size(); j++) {
                    PreparedStatement prepareStatement2 = con.prepareStatement("INSERT INTO VOUCHERV1" + " VALUES (?, ?)");
                    prepareStatement2.setInt(1, i);
                    System.out.println("parent" +j);
                    prepareStatement2.setInt(2, gg1.get(j));
                    prepareStatement2.execute();
                    System.out.println("Data inserted "+j);

                    
                    }
                    System.out.println("DATA INSERTED Into VOUCHERV1");
                    
                    
                        
                    
                    for (int j = 0; j < Equipment.gg.size(); j++) {
                        
                    ResultSet results3 = mystmt.executeQuery("Select * from SPORT_EQUIPMENT");
                        while(results3.next()){
                            System.out.println(Equipment.gg.get(j).getEquipment_Name());
                            if(Equipment.gg.get(j).getEquipment_Name().equals(results3.getString(4))){
                                PreparedStatement prepareStatement2 = con.prepareStatement("INSERT INTO Order_Sport" + " VALUES (?, ?)");
                                prepareStatement2.setInt(1, i);
                                System.out.println("parent" +j);
                                prepareStatement2.setInt(2, results3.getInt(1));
                                prepareStatement2.execute();
                                System.out.println("Data inserted order"+j);
                            }
                        }
                    
                    

                    
                    }
                    System.out.println("DATA INSERTED Into ");
                    
                    
                    
                    

                    JOptionPane.showMessageDialog(new JFrame(), "Your order has been confirmed");

                } catch (Exception e) {
                    System.out.println(e);
                }

                dispose();
                Review_GUI obj = new Review_GUI(name);
            } else if (ae.getActionCommand().equals(b4.getText())) {
                // print dishes

                bill = bill + "\nTotal Cost: " + totalbill + "\nTotal Cost with GST: " + (totalbill + totalbill * 0.2);

                System.out.println(obj1.getOrderEquipments().size());
                JOptionPane.showMessageDialog(new JFrame(), bill);
            }

        }
    }
}
