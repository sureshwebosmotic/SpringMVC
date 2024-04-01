package com.springmvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springmvc.entities.Skill;

public class SkillRowMapper implements RowMapper<Skill>{

	@Override
	public Skill mapRow(ResultSet rs, int rowNum) throws SQLException {
		Skill employee = new Skill();
		Integer employeeId = Integer.parseInt((rs.getString("skill_id")));
		employee.setId(employeeId);
		employee.setName(rs.getString("name"));
		employee.setEmployeeId(rs.getInt("employee_fid"));
        return employee; 
	}

}
