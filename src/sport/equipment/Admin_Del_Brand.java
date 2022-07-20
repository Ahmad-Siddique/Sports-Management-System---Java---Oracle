
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
import java.util.ArrayList;
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
public class Admin_Del_Brand extends JFrame{
    JLabel l1;
    JTextField t1;
    JButton b1,b2;
    
    Admin_Del_Brand(){
        setLayout(new BorderLayout());
        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1,1));
        
        l1 = new JLabel("WHICH BRAND DO YOU WANT TO DELETE?");
        t1 = new JTextField(30);
        
        p1.add(l1);p1.add(t1);
        
        b1 = new JButton("SUBMIT");
        b2 = new JButton("GO TO PREVIOUS MENU");
        
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        p2.add(b1);p2.add(b2);
        add(p1,BorderLayout.CENTER);
        add(p2,BorderLayout.SOUTH);
        
        MyActionListener a = new MyActionListener();
        b1.addActionListener(a);
        b2.addActionListener(a);
    }
    public class MyActionListener implements ActionListener, Serializable {

        public void actionPerformed(ActionEvent ae) {
            if (ae.getActionCommand().equals("SUBMIT")) {
                
                try{
                        Class.forName("oracle.jdbc.driver.OracleDriver");

                        Connection con=DriverManager.getConnection(
                        "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=sportsdb)))","sports","sports");
                        
                            Statement mystmt = con.createStatement();
                            int i =1;
                            ResultSet results1 = mystmt.executeQuery("Select * from BRAND");
                            while (results1.next()){
                            i += 1;
                            }
                            
                            PreparedStatement prepareStatement2 = con.prepareStatement("DELETE FROM BRANDADMIN WHERE BRAND_ID = "+ Integer.parseInt(t1.getText()));
                                               
                            prepareStatement2.execute();
                              PreparedStatement prepareStatement4 = con.prepareStatement("DELETE FROM ORDER_SPORT WHERE SPORT_EQUIPMENT_ID IN[ " + "(Select ID from SPORT_EQUIPMENT where BRAND_ID = "+Integer.parseInt(t1.getText())+")");
                                               
                            prepareStatement4.execute();
                            
                            
                            PreparedStatement prepareStatement = con.prepareStatement("DELETE FROM SPORT_EQUIPMENT WHERE BRAND_ID =? ");
                                  prepareStatement.setInt( 1,Integer.parseInt(t1.getText()))            ; 
                            prepareStatement.execute();
                            
                          
                            PreparedStatement prepareStatement1 = con.prepareStatement("DELETE FROM BRAND WHERE ID = "+ Integer.parseInt(t1.getText()));
                                               
                            prepareStatement1.execute();
                            
                            System.out.println("DATA Deleted FROM BRAND");
                            
                            
                              System.out.println("DATA Deleted FROM BRANDADMIN");
                            
                             JOptionPane.showMessageDialog(new JFrame(),"Brand Successfully Deleted");
                            
                            }
			catch(Exception e){ System.out.println(e);
                   }

                
                
                
                
                
                
                
//                Administration.deleteFromRestaurantFile(t1.getText());
//                Administration.deleteFromFoodFile(t1.getText());
               
            } else if (ae.getActionCommand().equals("GO TO PREVIOUS MENU")) {
                dispose();
                Admin_Equipment_GUI a = new Admin_Equipment_GUI();
            }

        }
    }
}


    