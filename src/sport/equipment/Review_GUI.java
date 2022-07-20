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

/**
 *
 * @author Dell
 */
public class Review_GUI extends JFrame{
    JLabel l1,l2,l3,l4,l5;
    JTextField t1,t2,t3,t4;
    JButton b1,b2;
    String name;
    
    Review_GUI(String n){
        name = n;
        setLayout(new BorderLayout());
        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1,1));
        
        l1 = new JLabel("============REVIEW=============");
        
        p1.add(l1);
        
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(2,2));
        
        l2 = new JLabel("How was the functionality of App (use _ for spaces): ");
        t1 = new JTextField(30);
        
        l3 = new JLabel("Rate our App (1-5)");
        t2 = new JTextField(30);
        
        p2.add(l2);p2.add(t1);p2.add(l3);p2.add(t2);
   
        add(p1,BorderLayout.NORTH);
        
        add(p2,BorderLayout.CENTER);
        
        b1 = new JButton("Submit");
        b2 = new JButton("GO to Main Menu");
        
        //another panel here for buttons
        JPanel p3 = new JPanel();
        p3.setLayout(new FlowLayout());
        
        p3.add(b1);p3.add(b2);
        
        add(p3,BorderLayout.SOUTH);
        
        MyActionListener a = new MyActionListener();
        b1.addActionListener(a);
        b2.addActionListener(a);
            
    }
    public class MyActionListener implements ActionListener, Serializable {

        public void actionPerformed(ActionEvent ae) {
            if (ae.getActionCommand().equals("Submit")) {
                
                try{
                        Class.forName("oracle.jdbc.driver.OracleDriver");

                        Connection con=DriverManager.getConnection(
                        "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=sportsdb)))","sports","sports");
                        
                            Statement mystmt = con.createStatement();
                            int i =1;
                            ResultSet results1 = mystmt.executeQuery("Select * from REVIEW");
                            while (results1.next()){
                            i += 1;
                            }
                            PreparedStatement prepareStatement = con.prepareStatement("INSERT INTO REVIEW"+ " VALUES (?, ?, ?, ?)");
                            
                            prepareStatement.setInt(1,i);
                            prepareStatement.setString(2,t1.getText());
                            prepareStatement.setInt(3,Integer.parseInt(t2.getText()));
                            prepareStatement.setInt(4,Customer_Login.login_val);
                            
        //                    prepareStatement.setString(6,t5.getText());
                            prepareStatement.execute();
                            System.out.println("DATA INSERTED Into Review");
                            }
			catch(Exception e){ System.out.println(e);
                   }
                
                
                
                
                
                
                
                
                
                Review obj = new Review(name,t1.getText(),Double.parseDouble(t2.getText()));
                Review.writeToFile(obj);
                 JOptionPane.showMessageDialog(new JFrame(),"Review added successfully. ");
                
            } else if (ae.getActionCommand().equals("GO to Main Menu")) {
                Main_Menu a = new Main_Menu();
            }
        }
        
    }
}

