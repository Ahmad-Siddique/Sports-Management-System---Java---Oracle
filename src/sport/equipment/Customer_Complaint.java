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
public class Customer_Complaint extends JFrame{
    JLabel l1,l2,l3,l4;
     JButton b1,b2,b3,b4,b5;
     JTextField t1,t2,t3; 
    Customer oo;
     Customer_Complaint(Customer obj1){
        oo=obj1;
        setLayout(new BorderLayout());
        setSize(500,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
       l1=new JLabel("Enter Complain: ");
       l2=new JLabel("Enter Date");
      
        
        b1=new JButton("Submit");
        t1=new JTextField(30);
        t2=new JTextField(30);
       l4=new JLabel("COMPLAINT BOX");
        
        b4=new JButton("Return to Previous Menu");
       
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(1,1));
        p2.add(l4);
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2,2));
        p.add(l1);
        p.add(t1);
        p.add(l2);
        p.add(t2);
      
        
         
         JPanel p1 = new JPanel();
          p1.setLayout(new GridLayout(2,1));
          p1.add(b1);
         p1.add(b4);
         
        
        
          add(p2,BorderLayout.NORTH);
        add(p,BorderLayout.CENTER);
        add(p1,BorderLayout.SOUTH);
       
       
        
       
       
        
        MyActionListener a=new MyActionListener();
        b1.addActionListener(a);
             b4.addActionListener(a);
        
        
       
     }

    private class MyActionListener implements ActionListener {

        public MyActionListener() {
        }

        public void actionPerformed(ActionEvent ae) {
            
           if (ae.getActionCommand().equals(b4.getText())) {
                dispose();
                Customer_MenuGUI obj = new Customer_MenuGUI(oo);
            }
                     else if(t1.getText().equals("") || t2.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please input all text fields..!!");
            }
            else if (ae.getActionCommand().equals(b1.getText())) {
                
                
                
              try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");

                    Connection con = DriverManager.getConnection(
                            "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=sportsdb)))", "sports", "sports");

                    Statement mystmt = con.createStatement();
                    int i = 1;
                    ResultSet results1 = mystmt.executeQuery("Select * from COMPLAINS");
                    while (results1.next()) {
                        i++;
                    }
                    PreparedStatement prepareStatement = con.prepareStatement("INSERT INTO COMPLAINS" + " VALUES (?, ?, ?, ?)");

                    prepareStatement.setInt(1, i);
                    prepareStatement.setString(2, t1.getText());
                    prepareStatement.setString(3, t2.getText());
                    prepareStatement.setInt(4, Customer_Login.login_val);
                    
                    
         
                    prepareStatement.execute();
                    System.out.println("DATA INSERTED Into Complaint BOX");
                    dispose();
                    
                    Customer_MenuGUI obj = new Customer_MenuGUI(oo);
                } catch (Exception e) {
                    System.out.println(e);
                    
                } 
                
                
                
                
                
                
                
                
                
            }
            
            
            

        }
    }
}
