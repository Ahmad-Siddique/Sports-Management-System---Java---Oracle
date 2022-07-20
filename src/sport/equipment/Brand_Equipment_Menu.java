/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Dell
 */
public class Brand_Equipment_Menu extends JFrame{
    JLabel l1,l2;
    JButton b1,b2,b3,b4;
    
    Brand_Equipment_Menu(String s){
        setLayout(new BorderLayout());
        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1,1));
        
        l1 = new JLabel("=========WELCOME TO EQUIPMENT MENU=============");
        p1.add(l1);
        
        b1 = new JButton("Add Equipments to Menu");
        b2 = new JButton("Update Price of your Equipment");
        b3 = new JButton("Display your Equipments");
        b4 = new JButton("GO TO PREVIOUS MENU");
        
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(4,1));
        
        p2.add(b1);p2.add(b2);p2.add(b3);p2.add(b4);
        
        l2 = new JLabel(s);
        add(p1,BorderLayout.NORTH);
        add(p2,BorderLayout.CENTER);
        
        MyActionListener a = new MyActionListener();
        b1.addActionListener(a);
        b2.addActionListener(a);
        b3.addActionListener(a);
        b4.addActionListener(a);
    }
    
    public class MyActionListener implements ActionListener, Serializable {

        public void actionPerformed(ActionEvent ae) {
            if (ae.getActionCommand().equals("Add Equipments to Menu")) {
                dispose();
                Brand_Add_Equipment a = new Brand_Add_Equipment(l2.getText());

            } else if (ae.getActionCommand().equals("Update Price of your Equipment")) {
                dispose();
                Brand_Update_Equipment b = new Brand_Update_Equipment(l2.getText());

            } else if (ae.getActionCommand().equals("Display your Equipments")) {
                Brand_Display_Equipment c = new Brand_Display_Equipment(l2.getText());

            } else if (ae.getActionCommand().equals("GO TO PREVIOUS MENU")) {
                Brand_MainMenu a = new Brand_MainMenu(l2.getText());

            }
        }
    }
}
