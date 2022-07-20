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
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class Credit_Card_Payment extends JFrame {
   JLabel l1,l2;
   JButton b2,b3;
   JTextField t1;
   String name;
   Credit_Card_Payment(String n){
        
        name =n;
        setLayout(new BorderLayout());
        setSize(500,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        
         l1=new JLabel("WELCOME TO Card Payment MENU");
        
       
        l2=new JLabel("Enter ur Card Number: ");
        b2=new JButton("Pay");
        b3=new JButton("Go to Next Menu");
        
        
         JPanel p1=new JPanel();
       p1.setLayout(new GridLayout(1,1));
       p1.add(l1);
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(1,1));
        
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(1,3));
        p2.add(l2);
        p2.add(t1);
        p2.add(b2);
        JPanel p3 = new JPanel();
        p3.setLayout(new GridLayout(1,1));
        p3.add(b2);
        
           add(p1,BorderLayout.NORTH);
         add(p,BorderLayout.CENTER);
         add(p2,BorderLayout.CENTER);
         add(p2,BorderLayout.SOUTH);
   }
    private class MyActionListener implements ActionListener {

        public MyActionListener() {
        }
          public void actionPerformed(ActionEvent ae){
              
              if(ae.getActionCommand().equals(b2.getText())){
                  if(t1.getText()!=""){
                      JOptionPane.showMessageDialog(new JFrame(), "PAID SUCCESSFULLY");
                      
                      Review_GUI r = new Review_GUI(name);
                  }
                  
              }
              
              
              
    }
    }
   
}