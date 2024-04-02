package com.springmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import com.springmvc.entities.Employee;
import com.springmvc.mapper.EmployeeRowMapper;

//This is a EmployeeDaoImpl class which implements the methods to perform database related operations using jdbcTemplate.
public class EmployeeDaoImpl implements EmployeeDao {

	private JdbcTemplate jdbcTemplate;

	public void setTemplate(JdbcTemplate jdbcTemplate) {    
	    this.jdbcTemplate = jdbcTemplate;    
	}    
	
	@Override
	public Employee selectEmployee(int employeeId) {
		String SELECT_EMPLOYEE_BY_ID = "select * from employee where employee_id =?";
		Employee employee = jdbcTemplate.queryForObject(SELECT_EMPLOYEE_BY_ID, new Object[]{employeeId},new EmployeeRowMapper());    
		return employee;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> retrievedEmployees = jdbcTemplate.query("select * from employee",new EmployeeRowMapper());   
		return retrievedEmployees;
	}

	@Override
	public int insertEmployee(Employee employee) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String INSERT_EMPLOYEES_SQL = "INSERT INTO employee" + "  (name, age, salary, birth_date) VALUES ('"+employee.getName()+"','"+employee.getAge()+"','"+employee.getSalary()+"','"+employee.getBirthDate()+"');";
		jdbcTemplate.update(
				  new PreparedStatementCreator() {
				    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				      return connection.prepareStatement(INSERT_EMPLOYEES_SQL, Statement.RETURN_GENERATED_KEYS);
				    }
				  }, keyHolder);
		return keyHolder.getKey().intValue();
	}

	@Override
	public int updateEmployee(Employee employee) {
		String UPDATE_EMPLOYEE_SQL = "update employee set name = '"+employee.getName()+"', age= "+employee.getAge()+", salary ="+employee.getSalary()+", birth_date ='"+employee.getBirthDate()+"' where employee_id = "+employee.getEmployeeId()+";";
		int rowUpdated = 0;
		rowUpdated = jdbcTemplate.update(UPDATE_EMPLOYEE_SQL);
		return rowUpdated;
	}

	@Override
	public int deleteEmployee(int id) {
		String DELETE_EMPLOYEE_SQL = "delete from employee where employee_id = "+id+";";
		int rowdeleted = 0;
		rowdeleted = jdbcTemplate.update(DELETE_EMPLOYEE_SQL);
		return rowdeleted;
	}
}
