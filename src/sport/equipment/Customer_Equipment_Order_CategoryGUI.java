
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
import javax.swing.JPanel;

public class Customer_Equipment_Order_CategoryGUI extends JFrame {

    JLabel l1, l2, l3;
    JButton b1, b2, b3, b4, b5, b6, b7;
    Customer oo;
    Equipment gg;
    Customer_Equipment_Order_CategoryGUI(Customer name1,Equipment e) {
        oo = name1;
        gg=e;
        setLayout(new BorderLayout());
        setSize(500, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        l1 = new JLabel("WELCOME TO CATEOGRY MENU");
        l3 = new JLabel("CATEGORIES");
        b1 = new JButton("FOOTBALL");
        b2 = new JButton("BADMINTON");
        b3 = new JButton("CRICKET");
        b4 = new JButton("HOCKEY");
        b6 = new JButton("Exit");
        b5 = new JButton("Return to Previous Menu");
        b7 = new JButton("Go to Checkout");

        JPanel p1 = new JPanel();

        p1.setLayout(new GridLayout(1, 1));
        p1.add(l1);
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(8, 1));
        p.add(l3);
        p.add(b1);
        p.add(b2);
        p.add(b3);
        p.add(b4);
        p.add(b5);
        p.add(b6);
        p.add(b7);

        add(p1, BorderLayout.NORTH);
        add(p, BorderLayout.CENTER);

        MyActionListener a = new MyActionListener();
        b1.addActionListener(a);
        b2.addActionListener(a);
        b3.addActionListener(a);
        b4.addActionListener(a);
        b6.addActionListener(a);
        b5.addActionListener(a);
        b7.addActionListener(a);

    }

    private class MyActionListener implements ActionListener {

        public MyActionListener() {
        }

        public void actionPerformed(ActionEvent ae) {
            if (ae.getActionCommand().equals("Return to Previous Menu")) {
                dispose();
                Customer_Order_EquipmentGUI obj = new Customer_Order_EquipmentGUI(oo);
            } else if (ae.getActionCommand().equals(b1.getText())) {
                // print dishes
                dispose();
                Equi_FootballGUI obj = new Equi_FootballGUI(b1.getText(), oo,gg);
            } else if (ae.getActionCommand().equals(b2.getText())) {
                // print dishes
                dispose();
                Equi_Badminton obj = new Equi_Badminton(b2.getText(), oo,gg);
            } else if (ae.getActionCommand().equals(b3.getText())) {
                // print dishes
                dispose();
                Equi_CricketGUI obj = new Equi_CricketGUI(b3.getText(), oo,gg);
            } else if (ae.getActionCommand().equals(b4.getText())) {
                // print dishes
                dispose();
                Equi_HockeyGUI obj = new Equi_HockeyGUI(b4.getText(), oo,gg);
            } else if (ae.getActionCommand().equals(b6.getText())) {
                // print dishes
                ArrayList<Equipment> order = Equipment.readFromFile();
                for (int i = 0; i < order.size(); i++) {
                    System.out.println("s");
                    Customer_Order obj = new Customer_Order(oo, order.get(i));
                    Customer_Order.writeToFile(obj);

                }
                Main_Menu a = new Main_Menu();
            } else if (ae.getActionCommand().equals(b7.getText())) {
                CheckoutGUI a = new CheckoutGUI(oo.getCustomer_Name(),gg);
            }

        }
    }
}
