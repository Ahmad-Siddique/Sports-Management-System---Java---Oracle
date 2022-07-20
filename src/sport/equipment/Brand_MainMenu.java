
package sport.equipment;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Brand_MainMenu extends JFrame{
    JLabel l1,l2; 
    JButton b1,b2,b3;
    
    
    Brand_MainMenu(String s){
        setLayout(new BorderLayout());
        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1,1));
        
        l1 = new JLabel("=======WELCOME TO BRAND MENU===========");
        p1.add(l1);
        
        b1 = new JButton("Update Name of Brand");
        b2 = new JButton("Check Your Menu");
        b3 = new JButton("Go to Previous Menu");
        
        l2 = new JLabel(s);
        
        JPanel p2= new JPanel();
        p2.setLayout(new GridLayout(3,1));
        p2.add(b1);p2.add(b2);p2.add(b3);
        
        add(p1,BorderLayout.NORTH);
        add(p2,BorderLayout.CENTER);
        
        MyActionListener a = new MyActionListener();
        b1.addActionListener(a);
        b2.addActionListener(a);
        b3.addActionListener(a);
}
    public class MyActionListener implements ActionListener, Serializable {

        public void actionPerformed(ActionEvent ae) {
            if(ae.getActionCommand().equals("Update Name of Brand")){ 
                dispose();
                Brand_Update_Name a = new Brand_Update_Name(l2.getText());
            }

            if (ae.getActionCommand().equals("Check Your Menu")) {
                dispose();
                Brand_Equipment_Menu f = new Brand_Equipment_Menu(l2.getText());
            }
            else if (ae.getActionCommand().equals("Go to Previous Menu")) {
                dispose();
                Brand_Login a = new Brand_Login();
              
            }
            
        }
    }
}

