/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccessLayer.DBConnect;

import java.sql.*;

/**
 *
 * @author alp_1
 */
public class DBConnect {
    
    public Connection connection;
    public Statement statement;
    public ResultSet resulSet;
    
    public DBConnect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeepsychology?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
            statement = connection.createStatement();
            
        }
        
        catch(Exception ex){
            System.out.println("Error:" + ex);
        }
    }
    public void getData(){
        try{
            String query = "select * from titles";
            resulSet = statement.executeQuery(query);
            System.err.println("Records From Database:");
            
            while(resulSet.next()){
                String title = resulSet.getString("titleName");
                int ID = resulSet.getInt("ID");
                
                System.err.println("ID:"+ ID + "title:" + title);
            }
            
        }catch(SQLException ex){
            System.err.println("Error:" + ex);
        }
    }
    
    
}
