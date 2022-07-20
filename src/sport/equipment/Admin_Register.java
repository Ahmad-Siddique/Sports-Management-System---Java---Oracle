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
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Admin_Register extends JFrame{
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;
    JTextField t1,t2,t3,t4,t5,t6,t7,t8;
    JButton b1,b2;
    Customer obj;
     Admin_Register(){
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
        b2=new JButton("Register");
        l1=new JLabel("WELCOME TO CUSTOMER MAIN MENU");
        l2=new JLabel("Enter Your CNIC");
        l3=new JLabel("Enter Your NAME");
        l4=new JLabel("Enter Password");
        l5=new JLabel("Enter Email");
       
        
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(1,1));
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(4,2));
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(1,1));
        p.add(b1);
       
         p.add(l1);
         p1.add(l2);
         p1.add(t1);
         p1.add(l3);
         p1.add(t2);
         p1.add(l4);
         p1.add(t3);
         p1.add(l5);
         p1.add(t4);
         
        
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
                  Main_Menu a = new Main_Menu();

              }
              else if(ae.getActionCommand().equals("Register")){
                  
                  if(t1.getText().equals("") || t2.getText().equals("") || t3.getText().equals("") || t4.getText().equals("") ){
                      JOptionPane.showMessageDialog(null, "Please input all text fields..!!");
                  }
                  else{
                      
                      
                       try{
                        Class.forName("oracle.jdbc.driver.OracleDriver");

                        Connection con=DriverManager.getConnection(
                        "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=sportsdb)))","sports","sports");
                        
                            Statement mystmt = con.createStatement();
                            int i =1;
                            ResultSet results1 = mystmt.executeQuery("Select * from ADMINISTRATION");
                            while (results1.next()){
                            i += results1.getInt(1);
                            }
                            PreparedStatement prepareStatement = con.prepareStatement("INSERT INTO ADMINISTRATION"+ " VALUES (?, ?, ?, ?)");
                            
                            prepareStatement.setInt(1,Integer.parseInt(t1.getText()));
                            prepareStatement.setString(2,t2.getText());
                            prepareStatement.setString(3,t3.getText());
                            prepareStatement.setString(4,t4.getText());
                           
        //                    prepareStatement.setString(6,t5.getText());
                            prepareStatement.execute();
                            System.out.println("DATA INSERTED Into ADMINISTRATION");
                            JOptionPane.showMessageDialog(null, "Admin Successfully Rgistered");
                             dispose();
                              Admin_GUI obj1=new Admin_GUI();
                            }catch(Exception e){ JOptionPane.showMessageDialog(null, "Error Occured...U have been already registered");;
                   }
                      
                      
                      
                      
//                      Customer obj=new Customer(t1.getText(),t2.getText(),Integer.parseInt(t3.getText()),Integer.parseInt(t4.getText()),t5.getText(),t6.getText(),t7.getText(),t8.getText());
//                      Customer.writeToFile(obj);
                      
                  }
                  
              }
              
    }
    }
}