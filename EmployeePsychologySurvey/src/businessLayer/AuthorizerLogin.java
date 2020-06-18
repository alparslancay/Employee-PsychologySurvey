package businessLayer;

import dataAccessLayer.DBConnect.DBConnect;
import dataAccessLayer.RepositoryClasses.RepositoryAnalystLoginInformation;
import dataAccessLayer.RepositoryClasses.RepositoryEmployeeLoginInformation;


public class AuthorizerLogin {

        DBConnect dbConnect = new DBConnect();
        RepositoryAnalystLoginInformation repositoryAnalystLogin;
        RepositoryEmployeeLoginInformation repositoryEmployeeLogin;
        public AuthorizerLogin(){
            
            repositoryAnalystLogin = new RepositoryAnalystLoginInformation(dbConnect);
            repositoryEmployeeLogin = new RepositoryEmployeeLoginInformation(dbConnect);
            
        }
	public boolean IsAnalyst(String userName, String password) {
            
            return repositoryAnalystLogin.GetID(userName, password) != -1;
	}
	
	public boolean IsEmployee(String userName, String password) {
          
            return repositoryEmployeeLogin.GetID(userName, password) != -1;
	}
}
