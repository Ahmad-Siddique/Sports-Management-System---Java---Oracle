/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sport.equipment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Administration implements Serializable{
    
    public static void Restaurant(){
        Scanner input=new Scanner(System.in);
         ArrayList<Brand> o=Brand.readFromFile();
         ArrayList<Equipment> a=Equipment.readFromFile();
        System.out.println("================ WELCOME TO ADMINISTRATION RESTAURANT MENU =======================");
        System.out.println("1. Show all Brand");
        System.out.println("2. Show all Equipments");
        System.out.println("3. Show dishes of specific Brand");
        System.out.println("4. Delete a Brand");
        System.out.println("5. Go to Previous Menu");
        int s=input.nextInt();
        if(s==1){
            System.out.println("========================= BRAND ======================");
            Brand.display();
            
        }
        else if(s==2){
            System.out.println("========================= ALL DISHES ======================");
            Equipment.display1();
        }
        else if(s==3){
            Brand.display();
            System.out.println("Enter Brand Name: ");
            String ss=input.next();
            Administration.Category(ss);
            
        }
        else if(s==4){
            Brand.display();
            System.out.println("Enter name of Brand: ");
            String n=input.next();
            deleteFromRestaurantFile(n);
            deleteFromFoodFile(n);
            
        }
       
        Restaurant();
    }
    public static void Customer(){
        Scanner input=new Scanner(System.in);
        System.out.println("==================== WELCOME TO ADMINISTRATION CUSTOMER MENU =======================");
        System.out.println("1. See Details of All Customers");
        System.out.println("2. See Details of Specific Customer");
        System.out.println("3. See Reviews of Customers");
        System.out.println("4. Go to Previous Menu");
        int s=input.nextInt();
        if(s==1){
            System.out.println("======================= ALL CUSTOMER DETAILS ======================");
            Customer.display();
        }
        else if(s==2){
            System.out.println("======================= SPECIFIC CUSTOMER DETAILS ======================");
            System.out.println("Enter name of Customer");
            String name=input.next();
            
            Customer.display(name);
            
        }
        else if(s==3){
            System.out.println("======================= ALL CUSTOMER REVIEWS ======================");
            Review.display();
        }
        
        Customer();
    }
    public static void deleteFromRestaurantFile(String name){
        ArrayList<Brand> list = Brand.readFromFile();
            for(Brand s:list)
        {
                if(name.equals(s.getBrand()))
        {
                    list.remove(s);
                    break;
        }
        }
        try
        {
            File f = new File("Restaurant.dat");

            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(f));
            for(int i=0;i<list.size();i++)
        {
                oos.writeObject(list.get(i));
        }
            oos.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
    public static void deleteFromFoodFile(String name){
        ArrayList<Equipment> list = Equipment.readFromFile();
//            for(Equipment s:list)
//        {
//                if(name.toUpperCase().equals(s.getRestaurant_Name().toUpperCase()))
//        {
//                    list.remove(s);
//                    break;
//        }
//        }
        try
        {
            File f = new File("Food.dat");

            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(f));
            for(int i=0;i<list.size();i++)
        {
                oos.writeObject(list.get(i));
        }
            oos.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
    public static void Category(String name){
        Scanner input=new Scanner(System.in);
        int count=0;
        ArrayList<Equipment> o=Equipment.readFromFile();
            for (int i = 0; i < o.size(); i++) {
//                if(o.get(i).getRestaurant_Name().toUpperCase().equals(name.toUpperCase())){
//                    System.out.println("============= Dish "+(count+1)+" =================");
//                    System.out.println("Equipment Name: "+o.get(i).getFood_Name());
//                    System.out.println("Price: "+o.get(i).getprice());
//                    count++;
//                }
            }
        }
    }

