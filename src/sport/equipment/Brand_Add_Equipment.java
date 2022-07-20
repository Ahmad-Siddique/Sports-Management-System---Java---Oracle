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
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Dell
 */
public class Brand_Add_Equipment extends JFrame{
    JLabel l1,l2,l3,l4,l5;
    JTextField t1,t2,t3,t4;
    JButton b1,b2;
    String b_name;
    Brand_Add_Equipment(String s){
        b_name=s;
        setLayout(new BorderLayout());
        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1,1));
        
        l1 = new JLabel("========WELCOME TO ADD EQUIPMENT MENU==========");
        
        p1.add(l1);
        
        l5 = new JLabel(s);
        l2 = new JLabel("Enter the name of equipment you want to add: ");
        t1 = new JTextField(30);
        
        l3 = new JLabel("Enter Category :" + "1. FOOTBALL 2. BADMINTON 3. HOCKEY 4. CRICKET");
        t2 = new JTextField(30);
        
        l4 = new JLabel("Enter the price of equipment: ");
        t3 = new JTextField(30);
        
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(3,2));
        
        p2.add(l2);p2.add(t1);p2.add(l3);p2.add(t2);p2.add(l4);p2.add(t3);
        
        b1 = new JButton("Submit");
        b2 = new JButton("GO TO PREVIOUS MENU");
        
        JPanel p3 = new JPanel();
        p3.setLayout(new FlowLayout());
        
        p3.add(b1);p3.add(b2);
        
        add(p1,BorderLayout.NORTH);
        add(p2,BorderLayout.CENTER);
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
                    ResultSet results = mystmt.executeQuery("Select * from BRAND");
                    int b_id=0;
                    while(results.next()){
                        if(results.getString(3).equals(b_name)){
                            b_id=results.getInt(1);
                            System.out.println("FOUND");
                        }
                    }
                     System.out.println(b_name);
                    ResultSet results1 = mystmt.executeQuery("Select * from SPORT_EQUIPMENT");
                    while (results1.next()){
                    i += results1.getInt(1);
                    }
                    PreparedStatement prepareStatement = con.prepareStatement("INSERT INTO SPORT_EQUIPMENT"+ " VALUES (?, ?, ?, ?, ?)");
                    prepareStatement.setInt(1, i);
                    prepareStatement.setString(2,t2.getText());
                    prepareStatement.setInt(3,Integer.parseInt(t3.getText()));
                    prepareStatement.setString(4,t1.getText());
                    prepareStatement.setInt(5,b_id);
//                    prepareStatement.setString(6,t5.getText());
                    prepareStatement.execute();
                    System.out.println("DATA INSERTED Into Sport Equipment");
                    }catch(Exception e){ System.out.println(e);
                   }
                
                
                Equipment obj = new Equipment(t1.getText(),t2.getText(),Integer.parseInt(t3.getText()));
                Equipment.writeToFile(obj);
                System.out.println("updated");

            } else if (ae.getActionCommand().equals("GO TO PREVIOUS MENU")) {
                dispose();
                Brand_Equipment_Menu a = new Brand_Equipment_Menu(l5.getText());

            }
    }
    }
}
