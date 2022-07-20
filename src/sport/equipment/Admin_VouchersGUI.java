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
public class Admin_VouchersGUI extends JFrame {

    JLabel l1;
    JButton b1, b2, b3,b4;

    Admin_VouchersGUI() {

        setLayout(new BorderLayout());
        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1));

        l1 = new JLabel("====================== WELCOME TO ADMINISTRATION MENU =======================");

        p1.add(l1);

        b1 = new JButton("Add Vouchers");
        b2 = new JButton("Delete Vouchers");
        
        b3 = new JButton("Go to Previous Menu");
        b4=new JButton("View Vouchers");

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(5, 1));

        p2.add(b1);
        p2.add(b2);
    p2.add(b4);
        p2.add(b3);

        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.CENTER);

        MyActionListener a = new MyActionListener();
        b1.addActionListener(a);
        b2.addActionListener(a);
        b3.addActionListener(a);
             b4.addActionListener(a);
      

    }

    public class MyActionListener implements ActionListener, Serializable {

        public void actionPerformed(ActionEvent ae) {
            if (ae.getActionCommand().equals("Add Vouchers")) {
                dispose();
                Admin_AddVoucher a = new Admin_AddVoucher();

            } else if (ae.getActionCommand().equals("Delete Vouchers")) {
                dispose();
                Admin_DeleteVouchers a = new Admin_DeleteVouchers();
            } 
            
            else if (ae.getActionCommand().equals("View Vouchers")) {
                
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
            } 
            else if (ae.getActionCommand().equals("Go to Previous Menu")) {
                dispose();
                Admin_GUI a = new Admin_GUI();
            }

        }
    }
}
