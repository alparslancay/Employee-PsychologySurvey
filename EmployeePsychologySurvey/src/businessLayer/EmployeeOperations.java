package businessLayer;
import dataAccessLayer.DBConnect.DBConnect;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import dataAccessLayer.RepositoryClasses.RepositoryEmployee;
import dataAccessLayer.RepositoryClasses.RepositoryEmployeeLoginInformation;

import entityLayer.*;

public class EmployeeOperations {

	public Employee answeringEmployee;
	EmployeeSurvey answeringSurvey;
        DBConnect dbConnect;
	
	
	RepositoryEmployee tableEmployeeDataID;
        
        RepositoryEmployeeLoginInformation repositoryEmployeeLogin;
        
	public EmployeeOperations(String userName, String password) {
                dbConnect = new DBConnect();
                
                tableEmployeeDataID = new RepositoryEmployee(dbConnect);
                repositoryEmployeeLogin = new RepositoryEmployeeLoginInformation(dbConnect);
                
		answeringEmployee = GetEmployee(userName,password);
		//answeringSurvey = IsThereSurvey();
                
	}
        
        public List<SurveyQuestion> GetAllQuestions(int surveyID){
            return tableEmployeeDataID.GetAllQuestions(surveyID);
        }
        
        public void InsertAnswers(List<SurveyQuestionAnswer> answers){
            
            for (SurveyQuestionAnswer currentAnswer : answers) {
                tableEmployeeDataID.InsertAnswer(currentAnswer);
            }

        }
        
        public EmployeeSurvey GetSurvey(){
            
            
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM");  
            LocalDateTime nowYearMonth = LocalDateTime.now();
            
            for (EmployeeSurvey currentSurvey : tableEmployeeDataID.GetAllSurvey()) {
                if(currentSurvey.surveyDate.startsWith(dtf.format(nowYearMonth)))
                    return currentSurvey;
            }
            return null;
        }
        
	
	public Employee GetEmployee(int employeeID) {
		
            return tableEmployeeDataID.GetEmployee(employeeID);
                
	}
        
        public Employee GetEmployee(String userName, String password){
            
            return GetEmployee(repositoryEmployeeLogin.GetID(userName,password));
            
        }
        
        public List<Employee> GetAllEmployees(String departmentName, int surveyID, int employeeID){
            return tableEmployeeDataID.GetNotEvaluatedEmployees(departmentName, surveyID, employeeID);
        }

	
	
	
	
	
}
