package com.springmvc.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.springmvc.entities.Employee;
import com.springmvc.services.EmployeeService;
import com.springmvc.services.EmployeeServiceImpl;

// This is a controller class which handles the request and return response to the user.
@Controller
public class EmployeeController {

	private EmployeeService employeeService;

	public void setEmployeeService(EmployeeServiceImpl employeeService) {
		this.employeeService=employeeService;
	}
	
	@RequestMapping("/")
	public String mainPage() {
		return "index";
	}
	
	@RequestMapping("/employee-form")
	public String showform(Model model) {
		model.addAttribute("employee", null);
		return "employee-form";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("employee") Employee employee, BindingResult result) {
		employeeService.insertEmployee(employee);
		return "redirect:/getAll";// will redirect to getAll request mapping
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateEmployee(@ModelAttribute("employee") Employee employee,  BindingResult result) {
		employeeService.updateEmployee(employee);
		return "redirect:/getAll";
	}
	
	@RequestMapping(value = "/editEmployee/{id}")
	public String getEmployeeById(@PathVariable int id, Model model) {
		Employee employee = employeeService.selectEmployee(id);
		model.addAttribute("employee", employee);
		return "employee-form";
	}

	

	@RequestMapping("/getAll")
	public String getAllEmployees(Model model) {
		List<Employee> employees = employeeService.getAllEmployee();
		model.addAttribute("employees", employees);
		return "employee-list";
	}

	@RequestMapping(value = "/deleteEmployee/{id}")
	public String delete(@PathVariable int id) {
		System.out.println("Hii");
		employeeService.deleteEmployee(id);
		return "redirect:/getAll";
	}
}
