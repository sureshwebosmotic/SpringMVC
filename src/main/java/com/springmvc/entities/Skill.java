package com.springmvc.entities;

//This is a Skill Entity Class.
public class Skill {
	private Integer id;

	private String name;

	private Integer employeeId;

	public Skill() {
	}

	public Skill(String name, Integer employeeId) {
		this.name = name;
		this.employeeId = employeeId;
	}

	public Skill(Integer id, String name, Integer employeeId) {
		this.id = id;
		this.name = name;
		this.employeeId = employeeId;
	}

	public Skill(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public String toString() {
		return name;
	}

	

}
