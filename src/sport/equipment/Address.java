/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sport.equipment;

import java.io.Serializable;

public class Address implements Serializable{
    
    private int home; 
    private int street;
    private String city;
    
    public Address(){
        home=4;
        street=4;
        city="RAWALPINDI";
    }
    public Address(int h, int s, String c){
        home= h;
        street= s;
        city= c;
    }
    
    public int getHome(){
        return home;
    }
    public void setHome(int h){
        home=h;
    }
    
     public int getStreet(){
        return street;
    }
     
     public void setStreet(int s){
        street=s;
    }
     
      public String getCity(){
        return city;
    }
      
    public void setCity(String c){
        city=c;
    }
    
      
}
