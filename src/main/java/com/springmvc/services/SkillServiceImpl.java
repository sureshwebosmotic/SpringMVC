package com.springmvc.services;

import java.util.Set;
import com.springmvc.dao.SkillDao;
import com.springmvc.dao.SkillDaoImpl;
import com.springmvc.entities.Employee;
import com.springmvc.entities.Skill;

//This is SkillServiceImpl interface which implements the service method and business logic to perform necessary operations.
public class SkillServiceImpl implements SkillService {

	private SkillDao skillDao;

	public void setSkillDao(SkillDaoImpl skillDao) {
		this.skillDao = skillDao;
	}

	@Override
	public int insertSkill(Skill skill) {
		return skillDao.insertSkill(skill);
	}

	@Override
	public Set<Skill> selectSkillsEmployeeId(int employeeId) {
		return skillDao.selectSkillsEmployeeId(employeeId);
	}

	@Override
	public int deleteSkillByEmployeeId(int employeeId) {
		return skillDao.deleteSkillByEmployeeId(employeeId);
	}

	@Override
	public void updateSkill(Employee employee) {
		Set<Skill> retrievedSkills = skillDao.selectSkillsEmployeeId(employee.getEmployeeId());
		Set<Skill> employeeSkills = employee.getSkills();
		// when new skill is added during edit
		for (Skill skill : employeeSkills) {
			if (!retrievedSkills.contains(skill)) {
				skillDao.insertSkill(skill);
			}
		}
		System.out.println("Skill");
		// when any skill is removed during edit
		for (Skill skill : retrievedSkills) {
			if (!employeeSkills.contains(skill)) {
				skillDao.deleteSkill(skill.getId());
			}
		}
	}
}
