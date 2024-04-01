package com.springmvc.dao;

import java.util.Set;

import com.springmvc.entities.Skill;

//This is a SkillDao Interface which defines Dao methods to perform database related operations.
public interface SkillDao {

	// save the skill.
	int insertSkill(Skill skill);

	// retrieve all the skills.
	Set<Skill> getAllSkills();

	// retrieve the skills by EmployeeId.
	public Set<Skill> selectSkillsEmployeeId(int employeeId);

	// update the skill
	int updateSkill(Skill skill);

	// delete the Skill by SkillId
	int deleteSkill(int id);

	// delete the Skill by SkillId
	int deleteSkillByEmployeeId(int employeeId);
}
