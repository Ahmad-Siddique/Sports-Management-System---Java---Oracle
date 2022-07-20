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
public class Admin_DeleteVouchers extends JFrame {

    JLabel l1;
    JTextField t1;
    JButton b1, b2, b3;

    Admin_DeleteVouchers() {
        setLayout(new BorderLayout());
        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1));

        l1 = new JLabel("Enter Voucher ID to Delete: ");
        t1 = new JTextField(30);

        p1.add(l1);
        p1.add(t1);

        b1 = new JButton("SUBMIT");
        b2 = new JButton("GO TO PREVIOUS MENU");
        b3 = new JButton("View vouchers");

        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        p2.add(b1);
        p2.add(b2);
        p2.add(b3);
        add(p1, BorderLayout.CENTER);
        add(p2, BorderLayout.SOUTH);

        MyActionListener a = new MyActionListener();
        b1.addActionListener(a);
        b2.addActionListener(a);
        b3.addActionListener(a);
    }

    public class MyActionListener implements ActionListener, Serializable {

        public void actionPerformed(ActionEvent ae) {
            if (ae.getActionCommand().equals("SUBMIT")) {

                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");

                    Connection con = DriverManager.getConnection(
                            "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=sportsdb)))", "sports", "sports");

                    Statement mystmt = con.createStatement();
                    int i = 1;
                    ResultSet results1 = mystmt.executeQuery("Select * from VOUCHER");
                    while (results1.next()) {
                        i += results1.getInt(1);
                    }
                    PreparedStatement prepareStatement = con.prepareStatement("DELETE FROM VOUCHER WHERE ID = " + Integer.parseInt(t1.getText()));

                    prepareStatement.execute();

                    
                    JOptionPane.showMessageDialog(new JFrame(), "Voucher Successfully Deleted");

                   

                    System.out.println("DATA Deleted");
                } catch (Exception e) {
                    System.out.println(e);
                }

                Administration.deleteFromRestaurantFile(t1.getText());
                Administration.deleteFromFoodFile(t1.getText());
                
            } else if (ae.getActionCommand().equals("View vouchers")) {
              

                try {

                    Class.forName("oracle.jdbc.driver.OracleDriver");

                    Connection con = DriverManager.getConnection(
                            "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=sportsdb)))", "sports", "sports");

                    Statement mystmt = con.createStatement();
                    ResultSet results = mystmt.executeQuery("Select * from VOUCHER");
                    String gg="";
                    int i=1;
                    while (results.next()) {
                        gg=gg+"\n=============== Voucher No: "+i+"============"+"\nVoucher ID: "+results.getInt(1)+"\nVoucher Name: "+results.getString(2)+"\nDiscount: "+results.getInt(3);
                        i++;
                    }
                    JOptionPane.showMessageDialog(new JFrame(), gg);

                } catch (Exception e) {

                }

                
            } else if (ae.getActionCommand().equals("GO TO PREVIOUS MENU")) {
                dispose();
                Admin_VouchersGUI a = new Admin_VouchersGUI();
            }

        }
    }
}
