package com.springmvc.services;

import java.util.Set;
import com.springmvc.entities.Employee;
import com.springmvc.entities.Skill;

// This is EmployeeService interface which is use to define service methods.
public interface SkillService {

	// save the skill.
	int insertSkill(Skill skill);

	// retrieve the skills by EmployeeId.
	public Set<Skill> selectSkillsEmployeeId(int employeeId);

	// delete the Skill by SkillId
	int deleteSkillByEmployeeId(int employeeId);

	// update the skill
	public void updateSkill(Employee employee);
}
