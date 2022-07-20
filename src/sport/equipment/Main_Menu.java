
package sport.equipment;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main_Menu extends JFrame{
    JLabel l1;
    JButton b1;
    JButton b2;
    JButton b3;
    JButton b4;
    
    Main_Menu(){
        setLayout(new BorderLayout());
        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout());
        
        l1 = new JLabel("---------SPORTS EQUIPMENT MANAGEMENT SYSTEM----------");
        
        b1 = new JButton("Enter the Customer Frame: ");
        b2 = new JButton("Enter the Brand Frame: ");
        b3 = new JButton("Enter the Administration Frame: ");
        
        p1.add(l1);
        add(p1,BorderLayout.PAGE_START);
        
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(3,1));
        p2.add(b1);
        p2.add(b2);
        p2.add(b3);
        
        add(p2,BorderLayout.CENTER);
        
        MyActionListener a = new MyActionListener();
        b1.addActionListener(a);
        b2.addActionListener(a);
        b3.addActionListener(a);
    }
    
    public class MyActionListener implements ActionListener, Serializable {

        public void actionPerformed(ActionEvent ae) {

            if (ae.getActionCommand().equals("Enter the Customer Frame: ")) {
                dispose();
                Customer_Menu a = new Customer_Menu();
            }
            else if (ae.getActionCommand().equals("Enter the Brand Frame: ")) {
                dispose();
                Brand_Menu a = new Brand_Menu(); 
            }
            else if (ae.getActionCommand().equals("Enter the Administration Frame: ")) {
                dispose();
                Admin_Login a = new Admin_Login();
                
            }

        }
    }
}
    
