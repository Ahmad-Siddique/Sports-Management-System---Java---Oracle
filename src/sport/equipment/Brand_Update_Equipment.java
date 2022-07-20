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
public class Brand_Update_Equipment extends JFrame {

    JLabel l1, l2, l3, l4, l5, l6,l7;
    JTextField t1, t2, t3, t4, t5;
    JButton b1, b2, b3;
    String name;

    Brand_Update_Equipment(String s) {
        setLayout(new BorderLayout());
        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        name = s;
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1));

        l1 = new JLabel("========WELCOME TO UPDATE PRICE EQUIPMENT MENU==========");
        
        p1.add(l1);
        l7=new JLabel("Enter equipment name: ");
         t4 = new JTextField(30);
        l4 = new JLabel("Enter the price of equipment: ");
        t3 = new JTextField(30);

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(2, 2));
        p2.add(l7);
        p2.add(t4);
        p2.add(l4);
        p2.add(t3);

        b1 = new JButton("Submit");
        b2 = new JButton("GO TO PREVIOUS MENU");
        b3 = new JButton("Check Equipments");

        JPanel p3 = new JPanel();
        p3.setLayout(new FlowLayout());

        p3.add(b1);
        p3.add(b2);
        p3.add(b3);

        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.CENTER);
        add(p3, BorderLayout.SOUTH);

        MyActionListener a = new MyActionListener();
        b1.addActionListener(a);
        b2.addActionListener(a);
        b3.addActionListener(a);
    }

    public class MyActionListener implements ActionListener, Serializable {

        public void actionPerformed(ActionEvent ae) {

            if (ae.getActionCommand().equals("Submit")) {

               

                    try {
                        Class.forName("oracle.jdbc.driver.OracleDriver");

                        Connection con = DriverManager.getConnection(
                                "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=sportsdb)))", "sports", "sports");

                        Statement mystmt = con.createStatement();
                        int gg = 0;
                        int i = 1;
                        int Equip_id = 0;
                        int base_price = 0;
                        int b_id=0;

                        b_id=Brand.BrandID(name);

                        String brand_equip = "";
                        int number = 1;
                        
                        System.out.println(b_id);
                        
                            ResultSet results1 = mystmt.executeQuery("Select * from SPORT_EQUIPMENT");
                            
                            while (results1.next()) {

                                if (results1.getInt(5)==b_id) {
                                   
                                        
                                    
                                    PreparedStatement prepareStatement = con.prepareStatement("UPDATE SPORT_EQUIPMENT SET BASE_PRICE = ? WHERE to_char(NAME) = ? AND BRAND_ID=?");
                                    prepareStatement.setInt(1, Integer.parseInt(t3.getText()));
                                    prepareStatement.setInt(3, b_id);
                                    prepareStatement.setString(2, t4.getText());
                                   
                                    

                                    prepareStatement.execute();
                                    gg = 1;
                                    
                                }

                            }
                            if (gg == 1) {
                                JOptionPane.showMessageDialog(new JFrame(), "Updated Successfully");
                                System.out.println("Changed");
                                Brand_Equipment_Menu a = new Brand_Equipment_Menu(name);
                            } else {
                                System.out.println("Not Matched");
                            }
                         

                    } catch (Exception e) {
                        System.out.println(e);
                         JOptionPane.showMessageDialog(new JFrame(), " No such equipment exists");
                    }

//                    Equipment obj = new Equipment(l5.getText(), t1.getText(), t2.getText(), Double.parseDouble(t3.getText()));
//                    Equipment.updateFood(t5.getText(), obj);
                
            } else if (ae.getActionCommand().equals("GO TO PREVIOUS MENU")) {
                dispose();
                Brand_Equipment_Menu a = new Brand_Equipment_Menu(l5.getText());

            } else if (ae.getActionCommand().equals("Check Equipments")) {
                
                
                
                
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");

                    Connection con = DriverManager.getConnection(
                            "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=sportsdb)))", "sports", "sports");

                    Statement mystmt = con.createStatement();
                    int gg = 0;
                    int i = 1;
                    int Equip_id = 0;
                    int base_price = 0;

                   int b_id=0;

                    b_id=Brand.BrandID(name);

                    String brand_equip = "";
                    int number = 1;
                    ResultSet results = mystmt.executeQuery("Select * from SPORT_EQUIPMENT");
                    while (results.next()) {
                        if (results.getInt(5) == b_id) {

                            brand_equip = brand_equip + "\n=========Equipment " + number + "===========\n Equipment Name: " + results.getString(4) + "\nPrice: " + results.getInt(3)+"\nCategory: "+results.getString(2);

                            Equip_id = results.getInt(1);
                            base_price = results.getInt(3);
                            number++;

                        }

                    }
                    if(brand_equip.equals("")){
                        JOptionPane.showMessageDialog(new JFrame(), "No Equipment to show");
                    }
                    else{
                        JOptionPane.showMessageDialog(new JFrame(), brand_equip);
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        }
    }
}
