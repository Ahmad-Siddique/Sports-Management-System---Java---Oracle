/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sport.equipment;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
public class Customer_Login extends JFrame {

    JLabel l1, l2, l3;
    JTextField t1, t2;
    JButton b1, b2;
    Customer obj;
    public static int login_val = 0;

    Customer_Login() {
        setLayout(new BorderLayout());
        setSize(500, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        t1 = new JTextField(30);
        t2 = new JTextField(30);

        b1 = new JButton("<= Go to Previous Menu");
        b2 = new JButton("Login");
        l1 = new JLabel("WELCOME TO CUSTOMER LOGIN");
        l2 = new JLabel("Enter Your CNIC");

        l3 = new JLabel("Enter Password");

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(1, 1));
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(2, 2));
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(1, 1));
        p.add(b1);

        p.add(l1);
        p1.add(l2);
        p1.add(t1);
        p1.add(l3);
        p1.add(t2);

        p2.add(b2);
        add(p, BorderLayout.NORTH);
        add(p1, BorderLayout.CENTER);
        add(p2, BorderLayout.SOUTH);

        MyActionListener a = new MyActionListener();
        b1.addActionListener(a);
        MyActionListener a1 = new MyActionListener();
        b2.addActionListener(a1);
        MyActionListener a2 = new MyActionListener();

    }

    private class MyActionListener implements ActionListener {

        public MyActionListener() {
        }

        public void actionPerformed(ActionEvent ae) {
            if (ae.getActionCommand().equals("<= Go to Previous Menu")) {
                dispose();
                Main_Menu a = new Main_Menu();

            } else if (ae.getActionCommand().equals("Login")) {

                if (t1.getText().equals("") || t2.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please input all text fields..!!");
                } else {

                    try {
                        Class.forName("oracle.jdbc.driver.OracleDriver");

                        Connection con = DriverManager.getConnection(
                                "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=sportsdb)))", "sports", "sports");

                        Statement mystmt = con.createStatement();
                        int i = 1;

                        boolean gg = false;
                     
                     
                        
                        
                        ResultSet result2=mystmt.executeQuery("Select * from USERLOGIN");
                        while (result2.next()){
                            i++;
                            }
                        
                           ResultSet results1 = mystmt.executeQuery("Select * from Customer");
                        System.out.println("gg1");
                        while (results1.next()) {
                             System.out.println("asdmnasjd");
                            System.out.println(results1.getString(1));
                            if (results1.getString(1).equals(t1.getText()) && results1.getString(8).equals(t2.getText())) {
                                System.out.println(results1.getString(1));
                                System.out.println(results1.getString(8));
                                
                                System.out.println("gg");
                                 PreparedStatement prepareStatement = con.prepareStatement("INSERT INTO USERLOGIN"+ " VALUES (?, ?, ?, ?)");
                                 login_val=i;
                                 prepareStatement.setInt(1,i);
                                 prepareStatement.setString(2,t1.getText());
                                 prepareStatement.setString(3,t2.getText());
                                 prepareStatement.setString(4,t1.getText());
                                 gg=true;
                                
                                prepareStatement.execute();
                                
                                Customer obj = new Customer(t1.getText(), results1.getString(2), Integer.parseInt(results1.getString(3)), Integer.parseInt(results1.getString(4)), results1.getString(5), results1.getString(6), results1.getString(7), t2.getText());
                                Customer.writeToFile(obj);
                                JOptionPane.showMessageDialog(null, "Customer Added Successfully\n Continuing to Customer Menu");
                                dispose();
                                Customer_MenuGUI obj1 = new Customer_MenuGUI(obj);
                                break;
                            }
                            else{
                                System.out.println("wew");
                            }
                            
                            
                            
                            
//                            System.out.println(results1.getString(1));
//                            System.out.println(results1.getString(8));
//                            System.out.println(results1.getString(1).equals(t1.getText()) && results1.getString(8).equals(t2.getText()));
//                            i++;
                        }

                        System.out.println(t1.getText());
                        System.out.println(t2.getText());

                        String cnic, name, city, email, phonenumber, password;
                        int house, street;

                        while (results1.next()) {
                            System.out.println("asdmnasjd");
                            System.out.println(results1.getString(1));
                            if (results1.getString(1).equals(t1.getText()) && results1.getString(8).equals(t2.getText())) {
                                System.out.println(results1.getString(1));
                                System.out.println(results1.getString(8));

                                System.out.println("gg");
//                                 PreparedStatement prepareStatement = con.prepareStatement("INSERT INTO USERLOGIN"+ " VALUES (?, ?, ?, ?)");
//                                 
//                                 prepareStatement.setInt(1,i);
//                                 prepareStatement.setString(2,t1.getText());
//                                 prepareStatement.setString(3,t2.getText());
//                                 prepareStatement.setString(4,t1.getText());
//                                 gg=true;
//                                
//                                prepareStatement.execute();

                                Customer obj = new Customer(t1.getText(), results1.getString(2), Integer.parseInt(results1.getString(3)), Integer.parseInt(results1.getString(4)), results1.getString(5), results1.getString(6), results1.getString(7), t2.getText());
                                Customer.writeToFile(obj);
                                JOptionPane.showMessageDialog(null, "Customer Added Successfully\n Continuing to Customer Menu");
                                dispose();
                                Customer_MenuGUI obj1 = new Customer_MenuGUI(obj);
                                break;
                            } else {
                                System.out.println("wew");
                            }
                        }
                        System.out.println("not inside");

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                }

            }

        }
    }
}
