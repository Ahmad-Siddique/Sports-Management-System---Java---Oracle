package sport.equipment;

import java.awt.BorderLayout;
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
public class Admin_Equipment_GUI extends JFrame {

    JLabel l1;
    JButton b1, b2, b3, b4, b5;

    Admin_Equipment_GUI() {
        setLayout(new BorderLayout());
        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1));

        l1 = new JLabel("================ WELCOME TO ADMINISTRATION RESTAURANT MENU =======================");

        p1.add(l1);

        b1 = new JButton("Show all Brands");
        b2 = new JButton("Show all Equipment");
        b3 = new JButton("Show equipments of specific Brand");
        b4 = new JButton("Delete a Brand");
        b5 = new JButton("GO TO PREVIOUS MENU");

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(5, 1));

        p2.add(b1);
        p2.add(b2);
        p2.add(b3);
        p2.add(b4);
        p2.add(b5);

        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.CENTER);

        MyActionListener a = new MyActionListener();
        b1.addActionListener(a);
        b2.addActionListener(a);
        b3.addActionListener(a);
        b4.addActionListener(a);
        b5.addActionListener(a);

    }

    public class MyActionListener implements ActionListener, Serializable {

        public void actionPerformed(ActionEvent ae) {
            if (ae.getActionCommand().equals("Show all Brands")) {

                try {

                    Class.forName("oracle.jdbc.driver.OracleDriver");

                    Connection con = DriverManager.getConnection(
                            "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=sportsdb)))", "sports", "sports");

                    Statement mystmt = con.createStatement();
                    ResultSet results = mystmt.executeQuery("Select * from BRAND");
                    int i=1;
                    String brand="";
                    while (results.next()) {
                        brand=brand+"\nBrand "+i+"\nBrand Name: "+results.getString(3)+"\nOwner Name: "+results.getString(2)+"\nCity: "+results.getString(4)+"\nEarning: "+results.getInt(5)+"\nPassword: "+results.getString(6);
                        i++;
                    }
                    JOptionPane.showMessageDialog(new JFrame(), brand);

                } catch (Exception e) {
                    System.out.println(e);
                }

//                ArrayList<Brand> o = Brand.readFromFile();
//                for (Brand a : o) {
//                    JOptionPane.showMessageDialog(new JFrame(), "Brand Name is: " + a.getBrand() + " \n" + "Brand owner name is: " + a.getOwner_name() + " \n" + "Brand city is: " + a.getBrand_City());
//                }

            } else if (ae.getActionCommand().equals("Show all Equipment")) {
                
                
                
                 try {

                    Class.forName("oracle.jdbc.driver.OracleDriver");

                    Connection con = DriverManager.getConnection(
                            "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=sportsdb)))", "sports", "sports");

                    Statement mystmt = con.createStatement();
                    ResultSet results = mystmt.executeQuery("select SPORT_EQUIPMENT.name,SPORT_EQUIPMENT.BASE_PRICE,SPORT_EQUIPMENT.EQUIPMENT,BRAND.BRAND_NAME from SPORT_EQUIPMENT Join BRAND on sport_equipment.brand_id=brand.id ");
                    int i=1;
                    String brand="";
                    while (results.next()) {
                        brand=brand+"\n==============Equipment "+i+" ================"+"\nEquipment Name: "+results.getString(1)+"\nEquipment Base Price: "+results.getString(2)+"\nType: "+results.getString(3)+"\nBrand Name: "+results.getString(4);
                        i++;
                    }
                    JOptionPane.showMessageDialog(new JFrame(), brand);

                } catch (Exception e) {
                     System.out.println(e);
                }
                
                
                
                
                
                
                
                

                ArrayList<Equipment> o = Equipment.readFromFile();
//                for (Equipment a : o) {
//                    JOptionPane.showMessageDialog(new JFrame(), "Brand name is: " + a.getRestaurant_Name() + " \n" + "Equipment name is: " + a.getFood_Name() + " \n" + "Equipment Category is: " + a.getCategory() + " \n" + "Price is: " + a.getprice());
//
//                }
            } else if (ae.getActionCommand().equals("Show equipments of specific Brand")) {
                dispose();
//                ArrayList<Brand> o = Brand.readFromFile();
//                for (Brand a : o) {
//                    JOptionPane.showMessageDialog(new JFrame(), "Brand Names are: " + a.getBrand() + " \n");
//                }
                Admin_Specific_Equip_Display a = new Admin_Specific_Equip_Display();

            } else if (ae.getActionCommand().equals("Delete a Brand")) {
                dispose();
                
                Admin_Del_Brand a = new Admin_Del_Brand();
                

            } else if (ae.getActionCommand().equals("GO TO PREVIOUS MENU")) {
                dispose();
                Admin_GUI a = new Admin_GUI();
            }

        }
    }
}
