package com.springmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.springmvc.entities.Employee;

//This is a EmployeeDaoImpl class which implements the methods to perform database related operations using jdbc.
public class EmployeeDaoImpl implements EmployeeDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SkillDao skillDao;

	public void setTemplate(JdbcTemplate jdbcTemplate) {    
	    this.jdbcTemplate = jdbcTemplate;    
	}    
	
	@Override
	public Employee selectEmployee(int employeeId) {
		String SELECT_EMPLOYEE_BY_ID = "select * from employee where employee_id =?";
		Employee employee = jdbcTemplate.queryForObject(SELECT_EMPLOYEE_BY_ID, new Object[]{employeeId},new BeanPropertyRowMapper<Employee>(Employee.class));    
		System.out.println(employee.getBirthDate());
		employee.setSkills(skillDao.selectSkillsEmployeeId(employeeId));
		return employee;
	}

	@Override
	public List<Employee> getAllEmployees() {
		return jdbcTemplate.query("select * from employee",new RowMapper<Employee>(){    
	        public Employee mapRow(ResultSet resultSet, int row) throws SQLException {    
	        	Employee employee = new Employee();
	    		Integer employeeId = Integer.parseInt((resultSet.getString("employee_id")));
	    		employee.setEmployeeId(employeeId);
	    		employee.setName(resultSet.getString("name"));
	    		employee.setAge(resultSet.getInt("age"));
	    		employee.setSalary(resultSet.getDouble("salary"));
	    		employee.setSkills(skillDao.selectSkillsEmployeeId(employeeId));
	    		//employee.setBirthDate(resultSet.getDate("birth_date"));
	    		
	            return employee;    
	        }    
	    });    
	}

	@Override
	public int insertEmployee(Employee employee) {
		System.out.println("Dao");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String INSERT_EMPLOYEES_SQL = "INSERT INTO employee" + "  (name, age, salary, birth_date) VALUES ('"+employee.getName()+"','"+employee.getAge()+"','"+employee.getSalary()+"','"+employee.getBirthDate()+"');";
		int rowInserted = jdbcTemplate.update(
				  new PreparedStatementCreator() {
				    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				      return connection.prepareStatement(INSERT_EMPLOYEES_SQL, Statement.RETURN_GENERATED_KEYS);
				    }
				  }, keyHolder);
		
		if(keyHolder.getKey().intValue()!=0) {
			employee.getSkills().forEach(skill -> {
				skill.setEmployeeId(keyHolder.getKey().intValue());
				skillDao.insertSkill(skill);
			});
			
		}
		return rowInserted;
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
		rowdeleted = skillDao.deleteSkillByEmployeeId(id);
		rowdeleted += jdbcTemplate.update(DELETE_EMPLOYEE_SQL);
		
		return rowdeleted;
	}
}
