package dataAccessLayer.RepositoryClasses;

import dataAccessLayer.DBConnect.DBConnect;
import java.util.List;
import entityLayer.AnalystLoginInformation;
import java.sql.SQLException;
import java.util.Stack;

public class RepositoryAnalystLoginInformation extends Repository {

        public RepositoryAnalystLoginInformation(DBConnect dbConnect){
            this.dbConnect = dbConnect;
            this.connection = dbConnect.connection;
            this.resulSet = dbConnect.resulSet;
            this.statement = dbConnect.statement;
        }
        
        public int GetID(String userName, String password){

            try{
            String query =  "SELECT ID FROM analyst_login_infos WHERE userName ='"+ userName+"' AND password = '"+ password + "'";
            
            resulSet = statement.executeQuery(query);
            resulSet.next();
            return resulSet.getInt("ID");
            }
            
            catch(SQLException ex){
            System.err.println("Error:" + ex);

            }
            
            return -1;
        }        

	public List<AnalystLoginInformation> GetAll() {
            
            List<AnalystLoginInformation> analystLoginInfos = new Stack<>();
        try{
            String query = "select * from analyst_login_infos";
            resulSet = statement.executeQuery(query);
            
            while(resulSet.next()){
                AnalystLoginInformation analystLoginInformation = new AnalystLoginInformation();
                analystLoginInformation.ID = resulSet.getInt("ID");
                analystLoginInformation.userName = resulSet.getString("userName");
                analystLoginInformation.password = resulSet.getString("password");
                
                analystLoginInfos.add(analystLoginInformation);
                
                return analystLoginInfos;
            }
            
        }
        
        catch(SQLException ex){
            System.err.println("Error:" + ex);

        }
         return null;
	}


}
