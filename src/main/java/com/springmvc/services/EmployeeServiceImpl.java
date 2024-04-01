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
		return employeeDao.insertEmployee(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {
		return employeeDao.getAllEmployees();
	}

	@Override
	public Employee selectEmployee(Integer employeeId) {
		return employeeDao.selectEmployee(employeeId);
	}

	@Override
	public int deleteEmployee(Integer employeeId) {
		return employeeDao.deleteEmployee(employeeId);
	}

	@Override
	public void updateEmployee(Employee employee){
		employeeDao.updateEmployee(employee);
		employee.getSkills().forEach(skill ->{
			skill.setEmployeeId(employee.getEmployeeId());
		});
		skillService.updateSkill(employee);
	}

}
