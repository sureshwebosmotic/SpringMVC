package com.springmvc.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
// This is a Employee Entity Class.
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;



public class Employee {

	private Integer employeeId;

	private String name;

	private Set<Skill> skills;

	private Integer age;

	private Double salary;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;

	public Employee() {
	}

	public Employee(String name, Set<Skill> skills, Integer age, Double salary, Date birthDate) {
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) throws ParseException {
//		System.out.println("SetDate");
//		System.out.println("birthDate : " + birthDate.getDate());
//		System.out.println("birthMonth : " + birthDate.getMonth());
//		System.out.println("birthYear : " + birthDate.getYear());
		this.birthDate = birthDate;// formatter.parse(birthDate.toString());
		System.out.println("this.birthDate : " + this.birthDate);
	}

}
