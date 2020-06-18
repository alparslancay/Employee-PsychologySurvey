/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccessLayer.RepositoryClasses;

import dataAccessLayer.DBConnect.DBConnect;
import entityLayer.Employee;
import entityLayer.EmployeeDepartment;
import entityLayer.EmployeeEvaluate;
import entityLayer.EmployeeIdentification;
import entityLayer.EmployeeSurvey;
import entityLayer.EmployeeTitle;
import entityLayer.SurveyQuestion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Stack;
/**
 *
 * @author alp_1
 */
public class RepositoryAnalyst extends Repository{
    
    public RepositoryAnalyst(DBConnect dbConnect){
        this.dbConnect = dbConnect;
        this.connection = dbConnect.connection;
        this.resulSet = dbConnect.resulSet;
        this.statement = dbConnect.statement;
    }
    
    public List<EmployeeEvaluate> GetEvaluates(int employeeID, int surveyID){
            List<EmployeeEvaluate> evaluates = new Stack<>();
            
            try{
            String query =  "CALL sp_evaluate_point"+"("+employeeID+","+surveyID+")";
            
            resulSet = statement.executeQuery(query);
            while(resulSet.next()){
            
            EmployeeEvaluate evaluate = new EmployeeEvaluate();
            
            evaluate.questionText = resulSet.getString("questionText");
            evaluate.pointValue = resulSet.getDouble("AVG(vswe.pointValue)");
            evaluates.add(evaluate);
                //System.out.println(evaluate.pointValue);
            }
            return evaluates;
            }
            
            catch(SQLException ex){
            System.err.println("Error:" + ex);

            }
            
            return null;          
    }
    
    public List<EmployeeEvaluate> GetSelfEvaluate(int employeeID, int surveyID){
            List<EmployeeEvaluate> evaluates = new Stack<>();
            
            try{
            String query =  "CALL sp_self_evaluate_point"+"("+employeeID+","+surveyID+")";
            
            resulSet = statement.executeQuery(query);
            while(resulSet.next()){
            
            EmployeeEvaluate evaluate = new EmployeeEvaluate();
            
            evaluate.questionText = resulSet.getString("questionText");
            evaluate.pointValue = resulSet.getDouble("AVG(vswe.pointValue)");
            evaluates.add(evaluate);
            }
            return evaluates;
            }
            
            catch(SQLException ex){
            System.err.println("Error:" + ex);

            }
            
            return null;    
    }
    
    public int GetSurreyID(LocalDateTime dt){
        try {
                        
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM");  
            String query = "CALL sp_find_survey("+"'"+ dtf.format(dt)+"%" +"')";
            
            resulSet = statement.executeQuery(query);
            
            return resulSet.getInt("ID");
        } 
        catch (SQLException ex) 
        {
            System.err.println("Error: "+ex);
        }
        
        return -1;
    }
    
    public void InsertQuestion(SurveyQuestion question){
                    try {
                String query = " INSERT INTO survey_questions (surveyID, questionText)"
                  + " values (?, ?)";

                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                
                preparedStmt.setInt(1, question.SurveyID);
                preparedStmt.setString(2, question.questionText);

                // execute the preparedstatement
                preparedStmt.execute();
            } 
            
            catch (SQLException ex) {
            System.err.println("Error:" + ex);
            }
        
    }
    
    public void InsertSurvey(EmployeeSurvey employeeSurvey){
                    try {
                String query = " INSERT INTO survey (surveyDate)"
                  + " values (?)";

                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setObject(1,employeeSurvey.surveyDate);

                // execute the preparedstatement
                preparedStmt.execute();
            } 
            
            catch (SQLException ex) {
            System.err.println("Error:" + ex);
            }
        
    }
    public List<Employee> GetEvaluatedEmployees(int surveyID){
            List<Employee> evaluatedEmployees = new Stack<>();
            
            try{
            String query =  "CALL sp_evaluated"+"("+surveyID+")";
            
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
            
            evaluatedEmployees.add(employee);
            }
            return evaluatedEmployees;
            }
            
            catch(SQLException ex){
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
}
