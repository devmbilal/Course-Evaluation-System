package qau.ces.data;

import java.sql.Connection;
import java.sql.DriverManager;

public class database {
    
    public static Connection connectDb(){
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=devmbilal", "devmbilal", "63013");
            return connect;
        }catch(Exception e){e.printStackTrace();}
        return null;
    }
    
}