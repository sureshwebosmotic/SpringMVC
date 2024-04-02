package com.springmvc.services;

import java.util.List;
import com.springmvc.entities.Employee;

// This is EmployeeService interface which is use to define service methods.
public interface EmployeeService {
	
	// save the employee.
	public int insertEmployee(Employee employee);

	// retrieve all the employees.
	public List<Employee> getAllEmployee();

	// retrieve the employee by employeeId
	public Employee selectEmployee(Integer employeeId);

	// delete the employee by employeeId
	public int deleteEmployee(Integer employeeId);

	// update the employee
	public int updateEmployee(Employee employee);
}
