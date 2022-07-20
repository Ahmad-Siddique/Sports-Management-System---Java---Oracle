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

public class Review implements Serializable {
    private String Customer_Name;
    private String Customer_Review;
    private double Customer_Rating;
    
    public Review(String n,String r,double rr){
        Customer_Name=n;
        Customer_Review=r;
        Customer_Rating=rr;
    }
     public String getCustomer_Name(){
        return Customer_Name;
    }
    public String getCustomer_Review(){
        return Customer_Review;
    }
    public double getCustomer_Rating(){
        return Customer_Rating;
    }
    public void setCustomer_Name(String s){
        Customer_Name=s;
    }
    public void setCustomer_Review(String s){
        Customer_Review=s;
    }
    public void setCustomer_Rating(int s){
        Customer_Rating=s;
    }
    
    public static void writeToFile(Review s) {

        try {
            File f = new File("Review.dat");

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
    
    public static ArrayList<Review> readFromFile() {
        ArrayList<Review> list = new ArrayList<Review>();

        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("Review.dat"));

            while (true) {
                Review obj = (Review) input.readObject();
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
    public static void display(){
        ArrayList<Review> o=Review.readFromFile();
        for (int i = 0; i < o.size(); i++) {
            System.out.println("Customer Name: "+o.get(i).getCustomer_Name());
            System.out.println("Customer Review: "+o.get(i).getCustomer_Review());
            System.out.println("Customer Rating: "+o.get(i).getCustomer_Rating());
            System.out.println("=================================================");
        }
    }
}
