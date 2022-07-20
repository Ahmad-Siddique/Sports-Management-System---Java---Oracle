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
import java.sql.*;
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
public class BrandRegister extends JFrame{
    JLabel l1,l2,l3,l4,l5,l6;
    JTextField t1,t2,t3,t4,t5;
    JButton b1,b2;
    
    BrandRegister(){
        setLayout(new BorderLayout());
        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(5,2));
        
        l1 = new JLabel("Enter Name of Restaurant");
        t1 = new JTextField(30);
        l2 = new JLabel("Enter Owner Name of Restaurant");
        t2 = new JTextField(30);
        l3 = new JLabel("Enter Earning of Restaurant");
        t3 = new JTextField(30);
        l4 = new JLabel("Enter City of Restaurant");
        t4 = new JTextField(30);
        l6 = new JLabel("Enter Password");
        t5 = new JTextField(30);
                
        l5 = new JLabel("============= WELCOME TO REGISTER BRAND MENU ====================");
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(1,1));
        
        p2.add(l5);
        add(p2,BorderLayout.NORTH);
        
        p1.add(l1);p1.add(t1);p1.add(l2);p1.add(t2);p1.add(l3);p1.add(t3);p1.add(l4);p1.add(t4);p1.add(l6);p1.add(t5);
        add(p1,BorderLayout.CENTER);
        
        b1 = new JButton("Submit");
        b2 = new JButton("GO to Previous Menu");
        
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
                if (Integer.parseInt(t3.getText()) > 40000) {
                    
                    try{
                        Class.forName("oracle.jdbc.driver.OracleDriver");

                        Connection con=DriverManager.getConnection(
                        "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=sportsdb)))","sports","sports");
                        
                    Statement mystmt = con.createStatement();
                    int i =1;
                    ResultSet results1 = mystmt.executeQuery("Select * from BRAND");
                    while (results1.next()){
                    i += results1.getInt(1);
                    }
                    PreparedStatement prepareStatement = con.prepareStatement("INSERT INTO BRAND"+ " VALUES (?, ?, ?, ?, ?,?)");
                    prepareStatement.setInt(1, i);
                    prepareStatement.setString(2,t2.getText());
                    prepareStatement.setString(3,t1.getText());
                    prepareStatement.setString(4,t4.getText());
                    prepareStatement.setInt(5,Integer.parseInt(t3.getText()));
                    prepareStatement.setString(6,t5.getText());
                    prepareStatement.execute();
                    System.out.println("DATA INSERTED Into Brand");
                    
                    PreparedStatement prepareStatement1 = con.prepareStatement("INSERT INTO BRANDADMIN"+ " VALUES (?, ?)");
                    prepareStatement1.setInt(1, 1);
                    prepareStatement1.setInt(2,i);
                    prepareStatement1.execute();
                    System.out.println("DATA INSERTED Into ADMINBRAND");
                    
                    
                   
                    }catch(Exception e){ System.out.println(e);
                   }
                    
                    
                    
                    
                    Brand a = new Brand(t2.getText(), t1.getText(), t4.getText(), Integer.parseInt(t3.getText()), t5.getText());
                    Brand.writeToFile(a);
                    JOptionPane.showMessageDialog(new JFrame(),"Brand added successfully. ");
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),"Your Brand does not have sufficient credit");
                }
            } else if (ae.getActionCommand().equals("GO to Previous Menu")) {
                Brand_Menu a = new Brand_Menu();
            }
        }
        
    }
}