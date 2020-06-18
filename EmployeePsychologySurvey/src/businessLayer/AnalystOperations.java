package businessLayer;

import dataAccessLayer.DBConnect.DBConnect;
import dataAccessLayer.RepositoryClasses.RepositoryAnalyst;
import entityLayer.Employee;
import entityLayer.EmployeeEvaluate;
import entityLayer.EmployeeSurvey;
import entityLayer.SurveyQuestion;
import java.time.LocalDateTime;
import java.util.List;

public class AnalystOperations {
    
	
        RepositoryAnalyst repositoryAnalyst;
        
        public AnalystOperations(){
            DBConnect dbConnect = new DBConnect();
            
            repositoryAnalyst = new RepositoryAnalyst(dbConnect);
        }
        
        public List<EmployeeEvaluate> GetSelfEvaluate(int employeeID, int surveyID){
           return repositoryAnalyst.GetSelfEvaluate(employeeID, surveyID);
        }
        
        public List<EmployeeEvaluate> GetEvaluates(int employeeID, int surveyID){
           return repositoryAnalyst.GetEvaluates(employeeID, surveyID);
        }
    
	public int GetSurveyID(LocalDateTime dateTime){
            return repositoryAnalyst.GetSurreyID(dateTime);
        }
        
        public void InsertSurvey(EmployeeSurvey employeeSurvey){
            repositoryAnalyst.InsertSurvey(employeeSurvey);
        }
        
        public void InsertQuestions(List<SurveyQuestion> questions ){
            for (SurveyQuestion question : questions) {
                repositoryAnalyst.InsertQuestion(question);
                
            }
        }
        
        public List<Employee> GetEvaluatedEmployees(int surveyID){
            return repositoryAnalyst.GetEvaluatedEmployees(surveyID);
        }
        
        public List<EmployeeSurvey> GetAllSurvey(){
            return repositoryAnalyst.GetAllSurvey();
        }

}
