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
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Equipment implements Serializable{
    
    private String Equipment_Name;
    private String Category;
    private double price;
    public static ArrayList<Equipment> gg=new ArrayList<Equipment>();
    
    public Equipment(){
        
    }
    Equipment( String rn,String c,double p){
        Equipment_Name=rn;
        Category=c;
        price=p;
    }
    
   
     public String getEquipment_Name() {
        return Equipment_Name;
    }

    public void setEquipment_Name(String n) {
        Equipment_Name = n;
    }
     public String getCategory() {
        return Category;
    }

    public void setCategory(String n) {
        Category = n;
    }
     public double getprice() {
        return price;
    }

    public void setprice(double n) {
        price = n;
    }
    
     public ArrayList<Equipment> getOrderEquipments() {
        return gg;
    }

   
    
     public static void writeToFile(Equipment s) {

        try {
            File f = new File("Food.dat");

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
     
     public static ArrayList<Equipment> readFromFile() {
        ArrayList<Equipment> list = new ArrayList<Equipment>();

        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("Food.dat"));

            while (true) {
                Equipment obj = (Equipment) input.readObject();
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
     public static boolean updateFood(String ss,Equipment s){
          
        ArrayList<Equipment> o=readFromFile();
        boolean zuzu=false;
        for(int i=0;i<o.size();i++){
            if(o.get(i).getEquipment_Name().equals(ss)){
                o.get(i).setEquipment_Name(s.getEquipment_Name());
                o.get(i).setCategory(s.getCategory());
                o.get(i).setprice(s.getprice());
                zuzu=true;
                break;
            }
        }
        try
        {
        File f = new File("Food.dat");

         ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(f));
        
            for (int i = 0; i < o.size(); i++) {
                oos.writeObject(o.get(i));
            }
    
      
       oos.close();
        }
        catch (IOException e)
        {

        }
        return zuzu;
    }
     public static boolean Exist(String equipment){
         
         try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(
            "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=bilal)))","bilal","Pakistan@1947");Statement mystmt = con.createStatement();
            boolean check = false;
            ResultSet results = mystmt.executeQuery("Select * from ticket");
            while (results.next()){
            if(results.getString(4).equals(equipment))
            check = true;
            if(check==true){
                return true;
            }
            else{
            return false;}
            }
//            
            }
            
            catch(Exception e){ System.out.println(e);}
//            return false;
           
//         
//         ArrayList<Equipment> o = readFromFile();
//         for (Equipment a : o) {
//             if(a.getRestaurant_Name().equals(ss)){
//                 if(a.getFood_Name().equals(equipment)){
//                     return true;
//                     
//                 }
//             }
//         }
         return false;
                 
     }
     //restaurant dishes
     public static void display(Equipment a){
        
            System.out.println("=============================================");
            System.out.println("Equipment: "+a.getEquipment_Name());
            System.out.println("Category: "+a.getCategory());
            System.out.println("Price: "+a.getprice());
            System.out.println("a");
           
        
     }
     
     
     public static int count_equipment(String s){
        int gg=0;
            try{
            
        
         Class.forName("oracle.jdbc.driver.OracleDriver");
         int number=1;
        Connection con=DriverManager.getConnection(
        "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=sportsdb)))","sports","sports");
        
        Statement mystmt = con.createStatement();
        ResultSet results = mystmt.executeQuery("Select * from SPORT_EQUIPMENT");
        while (results.next()){
            if(results.getString(2).equals(s))
                gg++;
        }
       
            return gg;
        
        }
      
        catch(Exception e){
             System.out.println(e);
        }
           
        return gg;
     }
     
     
     
     
     
     
     
     
     public static void display() {
        ArrayList<Equipment> o = readFromFile();
        for (Equipment a : o) {
            System.out.println("=============================================");
            System.out.println("Equipment: "+a.getEquipment_Name());
            System.out.println("Category: "+a.getCategory());
            System.out.println("Price: "+a.getprice());
           
        }
    }
          public static void display1() {
        ArrayList<Equipment> o = readFromFile();
        for (Equipment a : o) {
            System.out.println("=============================================");
//            System.out.println("Brand: "+a.getRestaurant_Name());
            System.out.println("Dish: "+a.getEquipment_Name());
            System.out.println("Category: "+a.getCategory());
            System.out.println("Price: "+a.getprice());
           
        }
    }
     public static String display1(String s){
         
         
         try{
            
        
         Class.forName("oracle.jdbc.driver.OracleDriver");
         int number=1;
        Connection con=DriverManager.getConnection(
        "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=sportsdb)))","sports","sports");
        String gg="";
        Statement mystmt = con.createStatement();
        ResultSet results = mystmt.executeQuery("Select * from SPORT_EQUIPMENT");
        while (results.next()){
            if(results.getString(2).equals(s)){
                
            
                gg=gg+ "\n==========Equipment " + number + "==========\n Equipment Name: " + results.getString(4) + "\nPrice: " + results.getInt(3)+"\nCategory: "+results.getString(2);
            number++;
            }
        }
        if(gg.equals("")){
            JOptionPane.showMessageDialog(null, "No Equipments in this Category");
        }
        else{
            return gg;
        }
        }
      
        catch(Exception e){
             System.out.println(e);
        }

         
         
         
         
         
         
         
         
         
         
         
         ArrayList<Equipment> o = readFromFile();
         boolean ss=false;
         String dish="";
         for (int i = 0; i < o.size(); i++) {
            if(o.get(i).getCategory().toUpperCase().equals(s.toUpperCase())){
                   dish += "========= EQUIPMENT "+(i+1)+"=============\n"+
                    "Equipment: "+o.get(i).getEquipment_Name()+
                    "\nPrice: "+o.get(i).getprice()+"\n";
                    ss=true;
                }
        }
         if(ss=false){
             return "No Dishes To print";
         }
         else{
             return dish;
         }
          }
    
}
