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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Brand implements Serializable, Interface_Equipment{

    private String Owner_name;
    private String brand_name;
    private String brand_city;
    private int earning;
    private String password;
   

   

    Brand(String on, String r, String rc, int re,String p) {
        Owner_name = on;
        brand_name = r;
        brand_city = rc;
        earning = re;
        password=p;

       
        
    }

    public String getOwner_name() {
        return Owner_name;
    }

    public void setOwner_name(String n) {
        Owner_name = n;
    }

    public String getBrand() {
        return brand_name;
    }

    public void setBrand(String n) {
        brand_name = n;
    }

    public String getBrand_City() {
        return brand_city;
    }

    public void setBrand_City(String n) {
        brand_city = n;
    }

    public double getBrand_Earning() {
        return earning;
    }

    public void setBrand_Earning(int n) {
        earning = n;
    }
    
    public String getPassword() {
        return brand_city;
    }

    public void setPassword(String n) {
        brand_city = n;
    }
    

    public boolean Checking_Brand_Name() {

        ArrayList<Brand> o = new ArrayList<Brand>();
        o = readFromFile();
        for (Brand a: o) {
            if (a.brand_name.equals(this.brand_name)) {
                return true;
            }
        }
        return false;
    }
    public static boolean Checking_Brand_Name(String n) {

        ArrayList<Brand> o = readFromFile();
        for (Brand a : o) {
            if (a.brand_name.equals(n)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean Checking_Brand_Password(String n) {

        ArrayList<Brand> o = readFromFile();
        for (Brand a : o) {
            if (a.password.equals(n)) {
                return true;
            }
        }
        return false;
    }

    public static void writeToFile(Brand s) {

        try {
            File f = new File("Restaurant.dat");

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

    public static ArrayList<Brand> readFromFile() {
        ArrayList<Brand> list = new ArrayList<Brand>();

        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("Restaurant.dat"));

            while (true) {
                Brand obj = (Brand) input.readObject();
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
    public static void updateBrandName(String name, String new_restaurant){
        
        
         try{
                        Class.forName("oracle.jdbc.driver.OracleDriver");

                        Connection con=DriverManager.getConnection(
                        "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=sportsdb)))","sports","sports");
                        
                    Statement mystmt = con.createStatement();
                    int gg=0;
                    int i =1;
                    ResultSet results1 = mystmt.executeQuery("Select * from BRAND");
                    while (results1.next()){
                        
                        if(results1.getString(3).equals(name)){
                            PreparedStatement prepareStatement = con.prepareStatement("UPDATE BRAND SET BRAND_NAME = ? WHERE to_char(BRAND_NAME) = ?");
                            prepareStatement.setString(1,new_restaurant);
                            prepareStatement.setString(2, name);
                            System.out.println(new_restaurant);
                            System.out.println(name);
                            prepareStatement.execute();
                            gg=1;
                        }
                       
                    }
                    if(gg==1){
                        
                        System.out.println("Changed");
                        Brand_MainMenu a = new Brand_MainMenu(new_restaurant);
                    }
                    else{
                        System.out.println("Not Matched");
                    }
                    
                    }catch(Exception e){ System.out.println(e);
                   }
        
        
        
        ArrayList<Brand> o=readFromFile();
        for(int i=0;i<o.size();i++){
            if(o.get(i).getBrand().equals(name)){
                o.get(i).setBrand(new_restaurant);
              
                break;
            }
        }
        try
        {
        File f = new File("Restaurant.dat");

         ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(f));
        
            for (int i = 0; i < o.size(); i++) {
                oos.writeObject(o.get(i));
            }
    
      
       oos.close();
        }
        catch (IOException e)
        {

        }
    }
    
    
    
    
    public static int BrandID(String name){
            try {
                        Class.forName("oracle.jdbc.driver.OracleDriver");

                        Connection con = DriverManager.getConnection(
                                "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=sportsdb)))", "sports", "sports");

                        Statement mystmt = con.createStatement();
                        int gg = 0;
                        int i = 1;
                        int Equip_id = 0;
                        int base_price = 0;

                        ResultSet results2 = mystmt.executeQuery("Select * from BRAND");
                        int b_id = 0;
                        while (results2.next()) {
                            if (results2.getString(3).equals(name)) {
                                b_id = results2.getInt(1);
                                System.out.println("FOUND");
                                return b_id;
                            }
                        }
        }
            catch(Exception e){
        System.out.println(e);
        
    }
            return 0;
    }

    public static void display() {
        ArrayList<Brand> o = readFromFile();
        for (Brand a : o) {
            System.out.println(a.getOwner_name());
            System.out.println(a.getBrand());
            System.out.println(a.getBrand_City());
            System.out.println(a.getBrand_Earning());
           
        }
    }
    public static void display(Brand a) {
        
            System.out.println("========================================================");
            System.out.println("Owner Name: "+a.getOwner_name());
            System.out.println("Name of Brand: "+a.getBrand());
            System.out.println("City of Brand: "+a.getBrand_City());
            System.out.println("Earning of Brand: "+a.getBrand_Earning());
            
            System.out.println("========================================================");
        
    }

}
