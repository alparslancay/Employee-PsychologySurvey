package dataAccessLayer.RepositoryClasses;

import dataAccessLayer.DBConnect.DBConnect;
import java.util.List;

import entityLayer.Employee;
import entityLayer.EmployeeDepartment;
import entityLayer.EmployeeIdentification;
import entityLayer.EmployeeSurvey;
import entityLayer.EmployeeTitle;
import entityLayer.SurveyQuestion;
import entityLayer.SurveyQuestionAnswer;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Stack;

public class RepositoryEmployee extends Repository{

        public RepositoryEmployee(DBConnect dbConnect){
        this.dbConnect = dbConnect;
        this.connection = dbConnect.connection;
        this.resulSet = dbConnect.resulSet;
        this.statement = dbConnect.statement;
        }
        
        
        public void InsertAnswer(SurveyQuestionAnswer questionAnswer){
            
            try {
                String query = " INSERT INTO survey_questions_answers (questionID, evaluatedEmployeeID, answeringEmployeeID, pointValue)"
                  + " values (?, ?, ?, ?)";

                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                
                preparedStmt.setInt(1, questionAnswer.questionID);
                preparedStmt.setInt(2, questionAnswer.evaluatedEmployeeID);
                preparedStmt.setInt(3, questionAnswer.answeringEmployeeID);
                preparedStmt.setInt(4, questionAnswer.pointValue);

                // execute the preparedstatement
                preparedStmt.execute();
            } 
            
            catch (SQLException ex) {
            System.err.println("Error:" + ex);
            }
        }
        
        public List<SurveyQuestion> GetAllQuestions(int surveyID){
            List<SurveyQuestion> questions = new Stack<>();
            
            try {
                String query = "SELECT sq.ID, sq.questionText FROM survey s INNER JOIN survey_questions sq ON s.ID = sq.surveyID AND s.ID = "+surveyID;
                resulSet = statement.executeQuery(query);
                
                while(resulSet.next()){
                    SurveyQuestion surveyQuestion = new SurveyQuestion();
                    surveyQuestion.ID = resulSet.getInt("ID");
                    surveyQuestion.questionText = resulSet.getString("questionText");
                    
                    questions.add(surveyQuestion);
                }
                
                return questions;
            } 
            
            catch (SQLException ex) {
            System.err.println("Error:" + ex);
            }
            
            return null;
        }
        
        public List<EmployeeSurvey> GetAllSurvey(){
            List<EmployeeSurvey> employeeSurveysList = new Stack<>();
            
            try {
                String query = "SELECT * FROM survey";
                
                resulSet = statement.executeQuery(query);
                
                while(resulSet.next()){
                    EmployeeSurvey employeeSurvey = new EmployeeSurvey();
                    employeeSurvey.ID = resulSet.getInt("ID");
                    employeeSurvey.surveyDate = resulSet.getString("surveyDate");
                    
                    employeeSurveysList.add(employeeSurvey);
                }
                
                return employeeSurveysList;
            } 
            
            catch (SQLException ex) {
            System.err.println("Error:" + ex);
            }
            
            return null;
        }
        
        public List<Employee> GetNotEvaluatedEmployees(String departmentName, int surveyID, int answeringEmployeeID){
            List<Employee> notEvaluatedEmployees = new Stack<>();
                        try{
            String query =  "CALL sp_not_evaluated"+"("+"'"+departmentName+"'"+","+surveyID+","+answeringEmployeeID+")";
            
            resulSet = statement.executeQuery(query);
            while(resulSet.next()){
            
            Employee employee = new Employee();
            employee.employeeIdentification = new EmployeeIdentification();            
            employee.employeeDepartment = new EmployeeDepartment();
            employee.employeeTitle = new EmployeeTitle();
            
            employee.employeeIdentification.firstName = resulSet.getString("firstName");
            employee.employeeIdentification.lastName = resulSet.getString("lastName");
            employee.employeeIdentification.employeeID = resulSet.getInt("employeeID");
            employee.employeeTitle.titleName = resulSet.getString("titleName");
            
            notEvaluatedEmployees.add(employee);
            }
            return notEvaluatedEmployees;
            }
            
            catch(SQLException ex){
            System.err.println("Error:" + ex);

            }
            
            return null;  
        }
        
        public Employee GetEmployee(int id){
            
            try{
            String query =  "CALL sp_get_employee_info_IDs"+"("+id+")";
            
            resulSet = statement.executeQuery(query);
            resulSet.next();
            
            Employee employee = new Employee();
            employee.employeeIdentification = new EmployeeIdentification();            
            employee.employeeDepartment = new EmployeeDepartment();
            employee.employeeTitle = new EmployeeTitle();
            
            employee.employeeIdentification.firstName = resulSet.getString("firstName");
            employee.employeeIdentification.lastName = resulSet.getString("lastName");
            employee.employeeIdentification.employeeID = resulSet.getInt("employeeID");
            employee.employeeDepartment.name = resulSet.getString("departmentName");
            employee.employeeTitle.titleName = resulSet.getString("titleName");
            
            return employee;
            }
            
            catch(SQLException ex){
            System.err.println("Error:" + ex);

            }
            
            return null;  
        }
        

}
