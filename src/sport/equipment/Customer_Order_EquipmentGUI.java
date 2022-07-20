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
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Customer_Order_EquipmentGUI extends JFrame {
    JLabel l1,l2;
    JButton b1,b2,b3,b4;
    Customer oo;
     Customer_Order_EquipmentGUI(Customer name1){
        oo=name1;
        setLayout(new BorderLayout());
        setSize(500,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        l1=new JLabel("WELCOME TO ORDER EQUIPMENT MENU");
        b1=new JButton("Search Equipment by Category");
       
        
        b3=new JButton("Return to Previous Menu");
        
        JPanel p1=new JPanel();
        p1.add(l1);
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2,1));
        
        p.add(b1);
         
        p.add(b3);
        
       
       
        add(p1,BorderLayout.NORTH);
        add(p,BorderLayout.CENTER);
       
       
        
        MyActionListener a=new MyActionListener();
        b1.addActionListener(a);
      
        b3.addActionListener(a);
        
        
        
        
       
     }

    private class MyActionListener implements ActionListener {

        public MyActionListener() {
        }
          public void actionPerformed(ActionEvent ae){
              if(ae.getActionCommand().equals("Return to Previous Menu")){
                  dispose();
                  Customer_MenuGUI obj=new Customer_MenuGUI(oo);
              }
              else if(ae.getActionCommand().equals(b1.getText())){
                  dispose();
                  Equipment obj1=new Equipment();
                  Customer_Equipment_Order_CategoryGUI obj=new Customer_Equipment_Order_CategoryGUI(oo,obj1);
              }
             
             
              
              
    }
    }
}