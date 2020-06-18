package entityLayer;

public class Employee {
	
	public EmployeeIdentification employeeIdentification;
	public EmployeeDepartment employeeDepartment;
	public EmployeeTitle employeeTitle;

        @Override
        public String toString(){
            return employeeIdentification.firstName + " " 
                    +  employeeIdentification.lastName + " " 
                    +  employeeTitle.titleName;
        }
}
