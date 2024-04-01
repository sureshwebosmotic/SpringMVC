package com.springmvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.springmvc.entities.Employee;
import com.springmvc.entities.Skill;
import com.springmvc.mapper.SkillRowMapper;

//This is a SkillDaoImpl class which implements the methods to perform database related operations using jdbc.
public class SkillDaoImpl implements SkillDao {

	private JdbcTemplate jdbcTemplate;

	public void setTemplate(JdbcTemplate jdbcTemplate) {    
	    this.jdbcTemplate = jdbcTemplate;    
	} 
	
	
	// save the skill.
	public int insertSkill(Skill skill) {
		String INSERT_SKILL_SQL = "INSERT INTO skill" + "  (name, employee_fid) VALUES " + " ('"+skill.getName()+"', '"+skill.getEmployeeId()+"');";
		int rowInserted = 0;
		return rowInserted = jdbcTemplate.update(INSERT_SKILL_SQL);
	}

	@Override
	public Set<Skill> getAllSkills() {
		
		List<Skill> retriveSkills = jdbcTemplate.query("select * from skill",new SkillRowMapper());  
		Set<Skill> skills = new HashSet<>(retriveSkills);
		return skills;
	};

	@Override
	public Set<Skill> selectSkillsEmployeeId(int employeeId) {
		List<Skill> retriveSkills = jdbcTemplate.query("select * from skill where employee_fid = '"+employeeId+"';",new SkillRowMapper()); 
		Set<Skill> skills = new HashSet<>(retriveSkills);
		return skills;
	}

	@Override
	public int updateSkill(Skill skill) {
		String UPDATE_SKILL_SQL = "update employee set name = '"+skill.getName()+"' where skill_id = '"+skill.getId()+"';";
		int rowUpdated = 0;
		return rowUpdated = jdbcTemplate.update(UPDATE_SKILL_SQL);
	}

	@Override
	public int deleteSkill(int id) {
		String DELETE_SKILL_SQL = "delete from skill where skill_id = '"+id+"';";
		int rowdeleted = 0;
		return rowdeleted = jdbcTemplate.update(DELETE_SKILL_SQL);
	}

	@Override
	public int deleteSkillByEmployeeId(int employeeId) {
		String DELETE_SKILL_SQL = "delete from skill where employee_fid = '"+employeeId+"';";
		int rowdeleted = 0;
		return rowdeleted = jdbcTemplate.update(DELETE_SKILL_SQL);
	}
}
