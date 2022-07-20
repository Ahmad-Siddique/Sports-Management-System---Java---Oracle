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

public class Customer_Update_InformationGUI extends JFrame {
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;
    JTextField t1,t2,t3,t4,t5,t6,t7,t8;
    JButton b1,b2;
    Customer obj;
    String ss;
     Customer_Update_InformationGUI(String cnic,Customer oo){
          ss=cnic;
         obj=oo;
       setLayout(new BorderLayout());
        setSize(500,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
       t1=new JTextField(30);
       t2=new JTextField(30);
       t3=new JTextField(30);
       t4=new JTextField(30);
       t5=new JTextField(30);
       t6=new JTextField(30);
       t7=new JTextField(30);
          t8=new JTextField(30);
        b1=new JButton("<= Go to Previous Menu");
        b2=new JButton("Update");
        l1=new JLabel("WELCOME TO CUSTOMER UPDATE MENU");
        
        l9=new JLabel("Update Password");
        
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(1,1));
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1,2));
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(1,1));
        p.add(b1);
       
         p.add(l1);
        
         p1.add(l9);
         p1.add(t8);
        
        p2.add(b2);
         add(p,BorderLayout.NORTH);
        add(p1,BorderLayout.CENTER);
        add(p2,BorderLayout.SOUTH);
       
        
        MyActionListener a=new MyActionListener();
        b1.addActionListener(a);
        MyActionListener a1=new MyActionListener();
        b2.addActionListener(a1);
        MyActionListener a2=new MyActionListener();
        
       
     }

    private class MyActionListener implements ActionListener {

        public MyActionListener() {
        }
          public void actionPerformed(ActionEvent ae){
              if(ae.getActionCommand().equals("<= Go to Previous Menu")){
                  dispose();
                  
              }
              else if(ae.getActionCommand().equals("Update")){
                  if(t8.getText().equals("")  ){
                      JOptionPane.showMessageDialog(null, "Please input all text fields..!!");
                  }
                  else{
                      
                       try{
                        Class.forName("oracle.jdbc.driver.OracleDriver");

                        Connection con=DriverManager.getConnection(
                        "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=sportsdb)))","sports","sports");
                        
                    Statement mystmt = con.createStatement();
                    int gg=0;
                    int i =1;
                    ResultSet results1 = mystmt.executeQuery("Select * from CUSTOMER");
                    while (results1.next()){
                        
                        if(results1.getString(1).equals(ss)){
                            System.out.println("EQUAL");
                           
                            
                            
                            PreparedStatement prepareStatement7 = con.prepareStatement("UPDATE CUSTOMER SET PASSWORD = ?  WHERE to_char(CNIC) = ?");
                            prepareStatement7.setString(1, t8.getText());
                            prepareStatement7.setString(2, ss);
                            prepareStatement7.execute();
                            
//                            
//                            
//                            System.out.println("Inserted Update");
//                        Customer_MenuGUI obj1=new Customer_MenuGUI(obj);
                            
                            
                            gg=1;
                        }
                       
                    }
                           System.out.println(ss);
                    if(gg!=1){
                         
                         System.out.println("Data Not Inserted"); 
                    }
                    else{
                         JOptionPane.showMessageDialog(null, "Password Updated");
                    }
                    
                    
                    }catch(Exception e){ System.out.println(e);
                   }
                      
                      
                      
                      
                      
                      
                      
                      
                      
                    obj.setCustomer_password(t8.getText());
//                      Customer.updateCustomerName(l8.getText(), obj);
//                      JOptionPane.showMessageDialog(null, "Customer Updated Successfully\n Returning to Customer Menu");
//                      Customer_MenuGUI obj1=new Customer_MenuGUI(obj);
                      dispose();
                       Customer_MenuGUI obj1 = new Customer_MenuGUI(obj);
                  }
                  
                  
              }
              else if (ae.getActionCommand().equals(b1.getText())) {
                dispose();
                Customer_MenuGUI obj1 = new Customer_MenuGUI(obj);
            }
    }
    }
}