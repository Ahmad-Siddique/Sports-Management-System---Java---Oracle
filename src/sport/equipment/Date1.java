/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sport.equipment;

import java.io.Serializable;

public class Date1 implements Serializable{
    private int month;
    private int day;
    private int year;
    
    public Date1(){
        month=0;
        day=0;
        year=0;
    }
    
    public Date1(int m, int d, int y){
        month = m; 
        day =d;
        year = y;
        
    }
    
    public int getMonth(){
        return month;
    }
    public int getDay(){
        return day;
    }
    public int getYear(){
        return year;
    }
     public void setMonth(int m){
       month=m;
    }
    public void setDay(int m){
        day=m;
    }
    public void setYear(int m){
       year=m;
    }
    
}

