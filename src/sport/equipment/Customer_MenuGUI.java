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
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Customer_MenuGUI extends JFrame {

    JButton b1, b2, b3, b4, b5, b6;
    Customer oo;

    Customer_MenuGUI(Customer obj1) {
        oo = obj1;
        setLayout(new BorderLayout());
        setSize(500, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        b1 = new JButton("Update your Information");
        b2 = new JButton("Order Equipment");
        b3 = new JButton("See your Details");
        b4 = new JButton("Return to Previous Menu");

        b5 = new JButton("Incident Log");
        b6 = new JButton("Complaint Box");

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(6, 1));
        p.add(b1);
        p.add(b2);
        p.add(b3);
        p.add(b5);
        p.add(b6);
        p.add(b4);

        add(p, BorderLayout.CENTER);

        MyActionListener a = new MyActionListener();
        b1.addActionListener(a);
        MyActionListener a1 = new MyActionListener();
        b2.addActionListener(a1);
        MyActionListener a2 = new MyActionListener();
        b3.addActionListener(a1);
        MyActionListener a3 = new MyActionListener();
        b4.addActionListener(a1);
        MyActionListener a4 = new MyActionListener();
        b5.addActionListener(a1);
        MyActionListener a5 = new MyActionListener();
        b6.addActionListener(a1);

    }

    private class MyActionListener implements ActionListener {

        public MyActionListener() {
        }

        public void actionPerformed(ActionEvent ae) {
            if (ae.getActionCommand().equals(b1.getText())) {
                dispose();
                Customer_Update_InformationGUI obj = new Customer_Update_InformationGUI(oo.getCustomer_CNIC(), oo);
            } else if (ae.getActionCommand().equals(b2.getText())) {
                dispose();
                Customer_Order_EquipmentGUI obj = new Customer_Order_EquipmentGUI(oo);
            } else if (ae.getActionCommand().equals(b3.getText())) {
                try {

                    Class.forName("oracle.jdbc.driver.OracleDriver");

                    Connection con = DriverManager.getConnection(
                            "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=sportsdb)))", "sports", "sports");

                    Statement mystmt = con.createStatement();
                    ResultSet results = mystmt.executeQuery("Select * from CUSTOMER");
                    String gg="";
                    while (results.next()) {
                        if(results.getString(1).equals(oo.getCustomer_CNIC())){
                            gg=gg+"\nCNIC: "+results.getString(1)+"\nName: "+results.getString(2)+"\nHouse No: "+results.getInt(3)+"\nStreet: "+results.getInt(4)+"\nCity: "+results.getString(5)+"\nEmail: "+results.getString(6)+"\nPhone Number: "+results.getString(7)+"\nPassword: "+results.getString(8);
                            break;
                        }
                    }
                    JOptionPane.showMessageDialog(null, gg);
                } catch (Exception e) {

                }

                Customer.display2(oo.getCustomer_Name());

            } else if (ae.getActionCommand().equals(b4.getText())) {
                dispose();
                Customer_RegisterGUI obj = new Customer_RegisterGUI();
            } else if (ae.getActionCommand().equals("Incident Log")) {
                dispose();
                Customer_IncidentLog obj = new Customer_IncidentLog(oo);
            } else if (ae.getActionCommand().equals("Complaint Box")) {
                dispose();
                Customer_Complaint obj = new Customer_Complaint(oo);
            }

        }
    }
}
