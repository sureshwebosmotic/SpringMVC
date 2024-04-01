package com.springmvc.controller;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springmvc.entities.Employee;
import com.springmvc.services.EmployeeService;
import com.springmvc.services.EmployeeServiceImpl;
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

//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//	    binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
//	        @Override
//	        public void setAsText(String text) throws IllegalArgumentException {
//	            try {
//	                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//	                dateFormat.setLenient(false);
//	                Date parsedDate = dateFormat.parse(text);
//	                setValue(parsedDate);
//	            } catch (ParseException e) {
//	                throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd");
//	            }
//	        }
//
//	        @Override
//	        public String getAsText() {
//	            Date date = (Date) getValue();
//	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//	            return dateFormat.format(date);
//	        }
//	    });
//	}

	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("employee") Employee employee, BindingResult result) {
		System.out.println(result.getFieldValue("birthDate"));

		System.out.println(employee.getBirthDate());
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		LocalDate date = LocalDate.parse(result.getFieldValue("birthDate").toString(), formatter);
//		employee.setBirthDate(date);
		employeeService.insertEmployee(employee);
		return "redirect:/getAll";// will redirect to getAll request mapping
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateEmployee(@ModelAttribute("employee") Employee employee,  BindingResult result) {
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		LocalDate date = LocalDate.parse(result.getFieldValue("birthDate").toString(), formatter);
//		employee.setBirthDate(date);
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
		employees.forEach(emp -> {
			System.out.println(emp.toString());
		});
		return "employee-list";
	}

	@RequestMapping(value = "/deleteEmployee/{id}")
	public String delete(@PathVariable int id) {
		System.out.println("Hii");
		employeeService.deleteEmployee(id);
		return "redirect:/getAll";
	}

}
