package com.springmvc.dao;

import java.util.List;

import com.springmvc.entities.Employee;

// This is a employeeDao Interface which defines Dao methods to perform database related operations.
public interface EmployeeDao {

	// retrieve the employee by employeeId
	Employee selectEmployee(int employeeId);

	// retrieve all the employees.
	List<Employee> getAllEmployees();

	// save the employee.
	int insertEmployee(Employee employee);

	// update the employee
	int updateEmployee(Employee employee);

	// delete the employee by employeeId
	int deleteEmployee(int id);
}
