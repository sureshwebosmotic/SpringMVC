package com.springmvc.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.springmvc.dao.EmployeeDao;
import com.springmvc.dao.EmployeeDaoImpl;
import com.springmvc.entities.Employee;

//This is EmployeeServiceImpl interface which implements the service method and business logic to perform necessary operations.
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDao employeeDao;

	public void setEmployeeDao(EmployeeDaoImpl employeeDao) {
		this.employeeDao=employeeDao;
	}
	
	@Autowired
	private SkillService skillService;

	@Override
	public int insertEmployee(Employee employee) {
		int rowInserted = 0;
		int generatedId = employeeDao.insertEmployee(employee);
		if(generatedId!=0) {
			employee.getSkills().forEach(skill -> {
				skill.setEmployeeId(generatedId);
				skillService.insertSkill(skill);
			});
		}
		return rowInserted;
	}

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> retrievedEmployees =  employeeDao.getAllEmployees();
		if(!retrievedEmployees.isEmpty()) {
			retrievedEmployees.forEach( employee -> employee.setSkills(skillService.selectSkillsEmployeeId(employee.getEmployeeId())));
		}
		return retrievedEmployees;
	}

	@Override
	public Employee selectEmployee(Integer employeeId) {
		Employee retrievedEmployee = employeeDao.selectEmployee(employeeId);
		if(retrievedEmployee!=null) {
			retrievedEmployee.setSkills(skillService.selectSkillsEmployeeId(employeeId));
		}
		return retrievedEmployee;
	}

	@Override
	public int deleteEmployee(Integer employeeId) {
		int rowDeleted = skillService.deleteSkillByEmployeeId(employeeId);
			rowDeleted += employeeDao.deleteEmployee(employeeId);
		return rowDeleted;
	}

	@Override
	public int updateEmployee(Employee employee){
		int rowUpdated = employeeDao.updateEmployee(employee);
		if(rowUpdated>0) {
			employee.getSkills().forEach(skill ->{
				skill.setEmployeeId(employee.getEmployeeId());
			});
			skillService.updateSkill(employee);
		}
		return rowUpdated;
	}
}
