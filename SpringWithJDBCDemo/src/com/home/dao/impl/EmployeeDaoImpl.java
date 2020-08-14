package com.home.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.home.dao.EmployeeDao;
import com.home.model.Employee;

public class EmployeeDaoImpl  implements EmployeeDao{
//Using JdbcDaoSupport class
//public class EmployeeDaoImpl extends JdbcDaoSupport implements EmployeeDao{

	//private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	/*public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate=new JdbcjdbcTemplate(dataSource);
	}*/
	
	public void setjdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/*@Override
	public void createEmployee(Employee employee) throws SQLException {
		
		Connection connection=null;
		PreparedStatement ps=null;
		try {
			connection=dataSource.getConnection();
			String sql="insert into employee_table(employee_name,email,gender,salary)values(?,?,?,?)";
			ps=connection.prepareStatement(sql);
			ps.setString(1, employee.getEmployeeName());
			ps.setString(2, employee.getEmail());
			ps.setString(3, employee.getGender());
			ps.setDouble(4, employee.getSalary());
			int i = ps.executeUpdate();
			if(i>0)
				System.out.println("Employee created successfully!!!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(connection !=null)
				connection.close();
		}

	}*/
	
	@Override
	public void createEmployee(Employee employee) throws SQLException {
		String sql="insert into employee_table(employee_name,email,gender,salary)values(?,?,?,?)";
		int update = jdbcTemplate.update(sql, new Object[] {employee.getEmployeeName(),employee.getEmail(),employee.getGender(),employee.getSalary()});
		//Using JdbcDaoSpport Cladss
		//int update = getJdbcTemplate().update(sql, new Object[] {employee.getEmployeeName(),employee.getEmail(),employee.getGender(),employee.getSalary()});
		if(update>0)
			System.out.println("Employee created successfully!!!");
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		String sql="select * from employee_table where employee_id=?";
		Employee employee = jdbcTemplate.queryForObject(sql, new EmployeeRowMapper(), employeeId);
		//Using JdbcDaoSpport Class
		//Employee employee = getJdbcTemplate().queryForObject(sql, new EmployeeRowMapper(), employeeId);
		return employee;
	}

	@Override
	public void deleteEmployeeById(int employeeId) {
		String sql="delete from employee_table where employee_id=?";
		int update = jdbcTemplate.update(sql,employeeId);
		//Using JdbcDaoSpport Class
		//int update = getJdbcTemplate().update(sql,employeeId);
		if(update>0)
			System.out.println("Employeee deleted successfully!!!");
	}

	@Override
	public void updateEmployeeById(int employeeId, String newEmail) {
		String sql="update employee_table set email=? where employee_id=?";
		int update = jdbcTemplate.update(sql, newEmail,employeeId);
		//Using JdbcDaoSpport Class
		//int update = getJdbcTemplate().update(sql, newEmail,employeeId);
		if(update>0)
			System.out.println("Employeee update successfully!!!");
	}

	@Override
	public List<Employee> getAllEmployees() {
		String sql="select * from employee_table";
		return jdbcTemplate.query(sql, new EmployeeRowMapper());
		//Using JdbcDaoSpport Class
		//return getJdbcTemplate().query(sql, new EmployeeRowMapper());
		 
	}

}
