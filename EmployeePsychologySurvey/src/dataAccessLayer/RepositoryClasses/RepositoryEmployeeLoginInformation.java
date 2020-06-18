package dataAccessLayer.RepositoryClasses;

import dataAccessLayer.DBConnect.DBConnect;
import java.util.List;

import entityLayer.EmployeeLoginInformation;
import java.sql.SQLException;
import java.util.Stack;

public class RepositoryEmployeeLoginInformation extends Repository{
    
        public RepositoryEmployeeLoginInformation(DBConnect dbConnect){
            
            this.dbConnect = dbConnect;
            this.connection = dbConnect.connection;
            this.resulSet = dbConnect.resulSet;
            this.statement = dbConnect.statement;
        }
        
        public int GetID(String userName, String password){
            
            try{
            String query =  "SELECT employeeID FROM employee_login_infos WHERE userName ='"+ userName+"' AND password = '"+ password + "'";
            
            resulSet = statement.executeQuery(query);
            resulSet.next();
            return resulSet.getInt("employeeID");
            }
            
            catch(SQLException ex){
            System.err.println("Error:" + ex);

            }
            
            return -1;
        }

	public List<EmployeeLoginInformation> GetAll() {
            
            List<EmployeeLoginInformation> employeeLoginInfos = new Stack<>();
        try{
            String query = "select * from employee_login_infos";
            resulSet = statement.executeQuery(query);
            
            while(resulSet.next()){
                EmployeeLoginInformation analystLoginInformation = new EmployeeLoginInformation();
                analystLoginInformation.ID = resulSet.getInt("employeeID");
                analystLoginInformation.userName = resulSet.getString("userName");
                analystLoginInformation.password = resulSet.getString("password");
                
                employeeLoginInfos.add(analystLoginInformation);
                
                return employeeLoginInfos;
            }
            
        }
        
        catch(SQLException ex){
            System.err.println("Error:" + ex);

        }
         return null;
	}

}
