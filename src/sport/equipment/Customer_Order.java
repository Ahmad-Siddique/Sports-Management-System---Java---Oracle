/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sport.equipment;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Customer_Order implements Serializable{
    private Customer CNIC;
    private Equipment Order;
    
    public Customer_Order(Customer n,Equipment o){
        CNIC=n;
        Order=o;
    }
    
    public void setCustomer_CNIC(Customer n){
        CNIC=n;
    }
    public Customer getCustomer_CNIC(){
        return CNIC;
    }
    
    public void setOrder(Equipment n){
        Order=n;
    }
    public Equipment getOrder(){
        return Order;
    }
     public static void writeToFile(Customer_Order s) {

        try {
            File f = new File("Customer_Order.dat");

            ObjectOutputStream oos;
            if (f.exists()) {
                oos = new MyObjectOutputStream(new FileOutputStream(f, true));
            } else {
                oos = new ObjectOutputStream(new FileOutputStream(f));
            }
            oos.writeObject(s);
            oos.close();
        } catch (IOException e) {

        }

    }
      public static ArrayList<Customer_Order> readFromFile() {
        ArrayList<Customer_Order> list = new ArrayList<Customer_Order>();

        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("Customer_Order.dat"));

            while (true) {
                Customer_Order obj = (Customer_Order) input.readObject();
                list.add(obj);
            }

        } catch (ClassNotFoundException c) {

        } catch (ClassCastException c) {

        } catch (EOFException e) {

        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }
        return list;
    }
      
    public static void display() {
        ArrayList<Customer_Order> o = Customer_Order.readFromFile();
        ArrayList<Customer_Order> b=new ArrayList<Customer_Order>();
        for (int i = 0; i < o.size(); i++) {
            boolean cond=false;
            for (int k = 0; k < b.size(); k++) {
                if(o.get(i).getCustomer_CNIC().getCustomer_CNIC().toUpperCase().equals(b.get(k).getCustomer_CNIC().getCustomer_CNIC().toUpperCase())){
                    cond=true;
                }
            }
            if(cond){
                System.out.println("Continuing");
                continue;
            }
            int count=0;
            System.out.println("Customer CNIC: "+o.get(i).getCustomer_CNIC());
            for (int j = 0; j < o.size(); j++) {
                if(o.get(i).getCustomer_CNIC().equals(o.get(j).getCustomer_CNIC())){
                System.out.println("=========== Equipment "+(count+1)+" ================");
                System.out.println("Equipment: "+o.get(j).getOrder().getEquipment_Name());
                System.out.println("Price: "+o.get(j).getOrder().getprice());
                count++;
                }
            }
            b.add(o.get(i));
        }
        
    }
}