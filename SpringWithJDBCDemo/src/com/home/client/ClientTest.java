package com.home.client;

import java.applet.AppletContext;
import java.sql.SQLException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.home.model.Employee;
import com.home.service.EmployeeService;

public class ClientTest {

	public static void main(String[] args) throws SQLException {
		
		ApplicationContext context=new ClassPathXmlApplicationContext("Beans.xml");
		EmployeeService service = context.getBean("employeeService", EmployeeService.class);
		//createEmployee(service);
		//getEmployeeById(service);
		getAllEmployees(service);
		//updateEmployee(service);
		//deleteEmployee(service);
        ((AbstractApplicationContext) context).close();
	}

	private static void deleteEmployee(EmployeeService service) {
		service.deleteEmployeeById(4);
		
	}

	private static void updateEmployee(EmployeeService service) {
		service.updateEmployeeById(4, "raja123@gmail.com");
		
	}

	private static void getAllEmployees(EmployeeService service) {
		List<Employee> employees = service.getAllEmployeesInfo();
		for (Employee employee : employees) {
			System.out.println(employee);
		}
		
	}

	private static void getEmployeeById(EmployeeService service) {
		Employee employee = service.fetchEmployeeById(2);
		System.out.println(employee);
		
	}

	private static void createEmployee(EmployeeService service) throws SQLException {
		Employee employee=new Employee();
		employee.setEmail("shubham@gmail.com");
		employee.setEmployeeName("Shubham");
		employee.setSalary(10000.00);
		employee.setGender("Male");
		service.addEmployee(employee);
	}

}
