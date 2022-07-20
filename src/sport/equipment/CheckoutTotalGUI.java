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
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CheckoutTotalGUI extends JFrame implements Serializable{
    JLabel l1,l2,l3,l4,l5;
    JButton b2,b3,b4,b5,b6;
    JTextField t1;
    int oo;
    Customer name2;
    String name;
    String bill="";
    double totalbill=0;
     CheckoutTotalGUI(String naem){
        name=naem;
        setLayout(new BorderLayout());
        setSize(500,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
       
       l1=new JLabel("WELCOME TO PAYMENT MENU");
      
       
        b4=new JButton("Pay on Delivery");
        
        b2=new JButton("Pay with Credit Card");
        
   

       JPanel p1=new JPanel();
       p1.setLayout(new GridLayout(1,1));
       p1.add(l1);
      
       p1.add(b4);
       p1.add(b2);
       
       
        add(p1,BorderLayout.NORTH);
         

        MyActionListener a1=new MyActionListener();
        b2.addActionListener(a1);
        
        b4.addActionListener(a1);
        
   
     }

    private class MyActionListener implements ActionListener {

        public MyActionListener() {
        }
          public void actionPerformed(ActionEvent ae){
              
            if(ae.getActionCommand().equals(b2.getText())){
                  
                  dispose();
                  Credit_Card_Payment a = new Credit_Card_Payment(name);
                  
              }
             else if(ae.getActionCommand().equals(b4.getText())){
                  // print dishes
                  dispose();
                  JOptionPane.showMessageDialog(new JFrame(),bill);
                  Review_GUI a = new Review_GUI(name);
              }
              
              
    }
    }
}
