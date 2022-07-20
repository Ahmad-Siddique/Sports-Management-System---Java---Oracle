
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


public class Admin_Login extends JFrame{
    JLabel l1,l2,l3; 
    JButton b1,b2,b3;
    JTextField t1,t2;
    public static int admin_log=0;
    
    Admin_Login(){
        setLayout(new BorderLayout());
        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(2,2));
        
        l1 = new JLabel("Enter the Admin CNIC: ");
        t1 = new JTextField(30);
        l2 = new JLabel("Enter password: ");
        t2 = new JTextField(30);
        
        p1.add(l1);p1.add(t1);
        p1.add(l2);p1.add(t2);
        add(p1,BorderLayout.CENTER);
        
        b1 = new JButton("Submit");
        b2 = new JButton("Go to Previous Menu");
        
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        
        p2.add(b1);p2.add(b2);
        
        add(p2,BorderLayout.SOUTH);
        
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
                    int gg=0;
                    int i =1;
                    ResultSet results1 = mystmt.executeQuery("Select * from ADMINISTRATION");
                    while (results1.next()){
                        
                        if(results1.getString(1).equals(t1.getText()) && results1.getString(3).equals(t2.getText())){
                            ResultSet results2 = mystmt.executeQuery("Select * from ADMINLOGIN");
                            while (results2.next()){
                            i += 1;
                            }
                            PreparedStatement prepareStatement = con.prepareStatement("INSERT INTO ADMINLOGIN"+ " VALUES (?, ?, ?, ?)");
                            
                            prepareStatement.setInt(1,i);
                            prepareStatement.setString(2,t1.getText());
                            prepareStatement.setString(3,t2.getText());
                            prepareStatement.setInt(4,Integer.parseInt(t1.getText()));
                            gg=1;
                             prepareStatement.execute();
                            System.out.println("DATA INSERTED Into ADMIN LOGIN");
                            admin_log=i;
                            break;
                            
                        }
                       
                    }
                    if(gg==1){
                        System.out.println("MATCHED");
                        JOptionPane.showMessageDialog(new JFrame(),"Logging in");
                        Admin_GUI a = new Admin_GUI();
                    }
                    else{
                        JOptionPane.showMessageDialog(new JFrame(),"No such Admin exists");
                        System.out.println("Not Matched");
                    }
                    
                    }catch(Exception e){ System.out.println(e);
                   }
                    
                
                
                
                
               
            }
            else if (ae.getActionCommand().equals("Go to Previous Menu")) {
                    Main_Menu a = new Main_Menu();
                }

            }
        }
    }

