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
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Dell
 */
public class Brand_Display_Equipment extends JFrame {

    JButton b1, b2;
    JLabel l1;
String name;
    Brand_Display_Equipment(String s) {
        name=s;
        setLayout(new BorderLayout());
        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(2, 1));

        b1 = new JButton("Display the equipments of your restaurant");
        p1.add(b1);

        b2 = new JButton("GO TO PREVIOUS MENU");
        p1.add(b2);

        l1 = new JLabel(s);
        add(p1, BorderLayout.CENTER);

        MyActionListener a = new MyActionListener();
        b1.addActionListener(a);
        b2.addActionListener(a);
    }

    public class MyActionListener implements ActionListener, Serializable {

        public void actionPerformed(ActionEvent ae) {
            if (ae.getActionCommand().equals("Display the equipments of your restaurant")) {

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

                            brand_equip = brand_equip + "\n==========Equipment " + number + "==========\n Equipment Name: " + results.getString(4) + "\nPrice: " + results.getInt(3)+"\nCategory: "+results.getString(2);

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

                ArrayList<Equipment> o = Equipment.readFromFile();
                int count = 1;
                for (int i = 0; i < o.size(); i++) {
//                    if (o.get(i).getRestaurant_Name().equals(l1.getText())) {
//                        
//                        JOptionPane.showMessageDialog(new JFrame(),"equipments " + count);
//                        JOptionPane.showMessageDialog(new JFrame(),"Equipment Name is: " + o.get(i).getFood_Name() + " \n" +"Equipment Category is: " + o.get(i).getCategory() + " \n" + "Equipment Price is: " + o.get(i).getprice() + "\n" );
//           
//                        
//                        count++;
//                    }
                }

            } else if (ae.getActionCommand().equals("GO TO PREVIOUS MENU")) {
                Brand_Equipment_Menu a = new Brand_Equipment_Menu(l1.getText());
            }
        }

    }
}
