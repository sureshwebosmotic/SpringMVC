package com.springmvc.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

//This is a Employee Entity Class.
public class Employee {

	private Integer employeeId;

	private String name;

	private Set<Skill> skills = new HashSet<>();

	private Integer age;

	private Double salary;
	
	private LocalDate birthDate;

	public Employee() {
	}

	public Employee(String name, Set<Skill> skills, Integer age, Double salary, LocalDate birthDate) {
		this.name = name;
		this.skills = skills;
		this.age = age;
		this.salary = salary;
		this.birthDate = birthDate;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Employee employeeId=" + employeeId + ", name=" + name + ", skills=" + skills + ", age=" + age
				+ ", salary=" + salary + ", birthDate=" + birthDate + "";
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = LocalDate.parse(birthDate);
	}
}
