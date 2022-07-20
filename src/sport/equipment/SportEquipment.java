
package sport.equipment;

import java.sql.*;
public class SportEquipment {

    public static void main(String[] args) {
        try{
            
        
         Class.forName("oracle.jdbc.driver.OracleDriver");

        Connection con=DriverManager.getConnection(
        "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=sportsdb)))","sports","sports");
        
        Statement mystmt = con.createStatement();
        ResultSet results = mystmt.executeQuery("Select * from CUSTOMER");
        while (results.next()){
            System.out.println(results.getString(2));
        }
        
        }
        catch(Exception e){
            
        }
        Main_Menu obj=new Main_Menu();
    }
    
}
