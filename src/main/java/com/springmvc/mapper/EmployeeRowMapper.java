package com.springmvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.springmvc.entities.Employee;

//This is a RowMapper class which maps the db tables rows to field of java object
public class EmployeeRowMapper implements RowMapper<Employee> {
	@Override
	public Employee mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Employee employee = new Employee();
		Integer employeeId = Integer.parseInt((resultSet.getString("employee_id")));
		employee.setEmployeeId(employeeId);
		employee.setName(resultSet.getString("name"));
		employee.setAge(resultSet.getInt("age"));
		employee.setSalary(resultSet.getDouble("salary"));
		employee.setBirthDate(resultSet.getDate("birth_date").toString());
		return employee;
	}
}
