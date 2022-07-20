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
 * @author Dell
 */
public class Admin_GUI extends JFrame {

    JLabel l1;
    JButton b1, b2, b3, b4, b7, b8, b9;

    Admin_GUI() {

        setLayout(new BorderLayout());
        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1));

        l1 = new JLabel("====================== WELCOME TO ADMINISTRATION MENU =======================");

        p1.add(l1);

        b1 = new JButton("Equipment");
        b2 = new JButton("Customer");
        b4 = new JButton("Vouchers");
        b7 = new JButton("Register Admin");
        b8 = new JButton("View Complaints");
        b9 = new JButton("View Incident Log");
        b3 = new JButton("Go to Previous Menu");

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(7, 1));

        p2.add(b1);
        p2.add(b2);
        p2.add(b4);
        p2.add(b7);
        p2.add(b8);
        p2.add(b9);
        p2.add(b3);

        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.CENTER);

        MyActionListener a = new MyActionListener();
        b1.addActionListener(a);
        b2.addActionListener(a);
        b3.addActionListener(a);
        b4.addActionListener(a);
        b7.addActionListener(a);
        b9.addActionListener(a);
        b3.addActionListener(a);
         b8.addActionListener(a);

    }

    public class MyActionListener implements ActionListener, Serializable {

        public void actionPerformed(ActionEvent ae) {
            if (ae.getActionCommand().equals("Equipment")) {
                dispose();
                Admin_Equipment_GUI a = new Admin_Equipment_GUI();

            } else if (ae.getActionCommand().equals("Customer")) {
                dispose();
                Admin_Customer_GUI a = new Admin_Customer_GUI();
            } else if (ae.getActionCommand().equals("Vouchers")) {
                dispose();
                Admin_VouchersGUI a = new Admin_VouchersGUI();
            } else if (ae.getActionCommand().equals("Go to Previous Menu")) {
                dispose();
                Main_Menu a = new Main_Menu();
            } else if (ae.getActionCommand().equals("Register Admin")) {
                dispose();
                Admin_Register a = new Admin_Register();
            } else if (ae.getActionCommand().equals("View Complaints")) {
                
                try {

                    Class.forName("oracle.jdbc.driver.OracleDriver");

                    Connection con = DriverManager.getConnection(
                            "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=sportsdb)))", "sports", "sports");

                    Statement mystmt = con.createStatement();
                    String gg="";
                    int i=1;
                    ResultSet results = mystmt.executeQuery("select customer.cnic,customer.name,COMPLAINS.COMPLAIN,complains.date_comp from CUSTOMER Join USERLOGIN on CUSTOMER.CNIC=USERLOGIN.CUSTOMER_CNIC Join COMPLAINS on USERLOGIN.ID=COMPLAINS.USERLOGIN_ID");
                    while (results.next()) {
                        gg=gg+"\n========== Customer "+i+"============="+"\nCustomer CNIC: "+results.getString(1)+"\nCustomer Name: "+results.getString(2)+"\nComplain: "+results.getString(3)+"\nDate: "+results.getString(4);
                    }
                    JOptionPane.showMessageDialog(new JFrame(),gg);
                } catch (Exception e) {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(new JFrame(),"ERROR COMPLAINT");
                }
                
            } else if (ae.getActionCommand().equals("View Incident Log")) {
               
                try {

                    Class.forName("oracle.jdbc.driver.OracleDriver");

                    Connection con = DriverManager.getConnection(
                            "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=sportsdb)))", "sports", "sports");

                    Statement mystmt = con.createStatement();
                    String gg="";
                    int i=1;
                    ResultSet results = mystmt.executeQuery("select customer.cnic,customer.name,INCIDENT_LOG.PRIORITY_LOSS,INCIDENT_LOG.equipment_unusable,incident_log.date_incident from CUSTOMER Join USERLOGIN on CUSTOMER.CNIC=USERLOGIN.CUSTOMER_CNIC Join INCIDENT_LOG on USERLOGIN.ID=INCIDENT_LOG.USERLOGIN_ID");
                    while (results.next()) {
                        gg=gg+"\n========== Customer "+i+"============="+"\nCustomer CNIC: "+results.getString(1)+"\nCustomer Name: "+results.getString(2)+"\nPriority Loss: "+results.getString(3)+"\nEquipment Unusable"+results.getString(4)+"\nDate: "+results.getString(5);
                    }
                    JOptionPane.showMessageDialog(new JFrame(),gg);
                } catch (Exception e) {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(new JFrame(),"ERROR INCIDENT");
                }
            }

        }
    }
}
