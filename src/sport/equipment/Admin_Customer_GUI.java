package sport.equipment;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Dell
 */
public class Admin_Customer_GUI extends JFrame {

    JLabel l1;
    JButton b1, b2, b3, b4,b5;

    Admin_Customer_GUI() {
        setLayout(new BorderLayout());
        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1));

        l1 = new JLabel("====================== WELCOME TO CUSTOMER ADMINISTRATION MENU =======================");

        p1.add(l1);

        b1 = new JButton("See Details of All Customers");
        b2 = new JButton("See Details of Specific Customer");
        b5 = new JButton("See Total Amount and Number of Order of All Customers");
        b3 = new JButton("See Reviews of Customers");
        b4 = new JButton("Go to Previous Menu");

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(5, 1));

        p2.add(b1);
        p2.add(b2);
        p2.add(b5);
        p2.add(b3);
        p2.add(b4);

        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.CENTER);

        MyActionListener a = new MyActionListener();
        b1.addActionListener(a);
        b2.addActionListener(a);
        b3.addActionListener(a);
        b4.addActionListener(a);
          b5.addActionListener(a);
    }

    public class MyActionListener implements ActionListener, Serializable {

        public void actionPerformed(ActionEvent ae) {
            if (ae.getActionCommand().equals("See Details of All Customers")) {

                try {

                    Class.forName("oracle.jdbc.driver.OracleDriver");

                    Connection con = DriverManager.getConnection(
                            "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=sportsdb)))", "sports", "sports");

                    Statement mystmt = con.createStatement();
                    ResultSet results = mystmt.executeQuery("Select * from CUSTOMER");
                    String gg="";
                    int i=1;
                    while (results.next()) {
                        gg=gg+"\n=====Customer "+i+" =======\nCustomer CNIC: "+results.getString(1)+"\nCustomer Name: "+results.getString(2)+"\nHouse No: "+results.getInt(3)+"\nStreet No: "+results.getInt(4)+"\nCity: "+results.getString(5)+"\nEmail: "+results.getString(6)+"\nPhone No: "+results.getString(7)+"\nPassword: "+results.getString(8);
                        i++;
                    }
                    JOptionPane.showMessageDialog(new JFrame(), gg);
                } catch (Exception e) {

                }

//                dispose();

//                ArrayList<Customer> o = Customer.readFromFile();
//
//                int count = 0;
//                String e = "";
//                for (Customer a : o) {
//
////                    e+=" CUSTOMER " + (count + 1)+"\n"+a.getCustomer_Name() + " " + a.Customer_Address.getHome() + " " + a.Customer_Address.getStreet() + " " + a.Customer_Address.getCity() + " " + a.getemail() + " " + a.getphone_number()+"\n" + "-------------------------------------" + "\n";
////                    JOptionPane.showMessageDialog(new JFrame(), " CUSTOMER " + (count + 1));
////                    JOptionPane.showMessageDialog(new JFrame(), a.getCustomer_Name() + " " + a.Customer_Address.getHome() + " " + a.Customer_Address.getStreet() + " " + a.Customer_Address.getCity() + " " + a.getemail() + " " + a.getphone_number());
//                    count++;
//
//                }
//                JOptionPane.showMessageDialog(new JFrame(), e);

            } else if (ae.getActionCommand().equals("See Details of Specific Customer")) {
                dispose();
                Admin_Specific_Cust a = new Admin_Specific_Cust();

            }
            
             else if (ae.getActionCommand().equals("See Total Amount and Number of Order of All Customers")) {
                 try {

                    Class.forName("oracle.jdbc.driver.OracleDriver");

                    Connection con = DriverManager.getConnection(
                            "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=sportsdb)))", "sports", "sports");

                    Statement mystmt = con.createStatement();
                    ResultSet results = mystmt.executeQuery("select to_char(customer.name),SUM(order_cus.price),COUNT(ORDER_CUS.ID) from CUSTOMER JOIN USERLOGIN on customer.cnic=userlogin.customer_cnic JOIN CUSTOMER_ORDER on userlogin.id=customer_order.userlogin_id JOIN ORDER_CUS on customer_order.order_cus_id=order_cus.id group by to_char(customer.name),to_char(customer.cnic)");
                    int i=1;
                    String gg="";
                    while (results.next()) {
                        gg=gg+"\n==============Customer: "+i+" ========================="+"\nCustomer Name: "+results.getString(1)+"\nTotal Amount: "+results.getString(2)+"\nTotal Orders: "+results.getString(3);
                        i++;
                    }
                    JOptionPane.showMessageDialog(new JFrame(), gg);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
            
            
            
            
            
            
            else if (ae.getActionCommand().equals("See Reviews of Customers")) {
                
                try {

                    Class.forName("oracle.jdbc.driver.OracleDriver");

                    Connection con = DriverManager.getConnection(
                            "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=sportsdb)))", "sports", "sports");

                    Statement mystmt = con.createStatement();
                    ResultSet results = mystmt.executeQuery("select customer.cnic,customer.name,REVIEW.CUSTOMER_REVIEW,REVIEW.CUSTOMER_RATING from CUSTOMER Join USERLOGIN on CUSTOMER.CNIC=USERLOGIN.CUSTOMER_CNIC Join REVIEW on USERLOGIN.ID=review.userlogin_id order by REVIEW.CUSTOMER_RATING desc");
                    int i=1;
                    String gg="";
                    while (results.next()) {
                        gg=gg+"\n==============Customer: "+i+" ========================="+"\nCustomer CNIC: "+results.getString(1)+"\nCustomer NAME: "+results.getString(2)+"\nCustomer REVIEW: "+results.getString(3)+"\nCustomer RATING: "+results.getInt(4);
                        i++;
                    }
                    JOptionPane.showMessageDialog(new JFrame(), gg);
                } catch (Exception e) {
                    System.out.println(e);
                }
                
                
                
                
                
                
                
                
//                dispose();
//                ArrayList<Review> o = Review.readFromFile();
//                for (int i = 0; i < o.size(); i++) {
//                    JOptionPane.showMessageDialog(new JFrame(), "Customer name is: " + o.get(i).getCustomer_Name() + " \n" + "Customer Review: " + o.get(i).getCustomer_Review() + " \n" + "Customer Rating: " + o.get(i).getCustomer_Rating() + " \n");
//
//                }
            } else if (ae.getActionCommand().equals("Go to Previous Menu")) {
                dispose();
                Admin_GUI a = new Admin_GUI();
            }

        }
    }
}
