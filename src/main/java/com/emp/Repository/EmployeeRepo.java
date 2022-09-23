package com.emp.Repository;

import javax.enterprise.context.ApplicationScoped;


import org.springframework.data.jpa.repository.JpaRepository;

import com.emp.model.Employee;


public interface EmployeeRepo    {


	public Employee saveEmp(Employee employee);
	
	public Employee getEmp(int id);
	
	boolean updateEmp(Employee employee);
	
	boolean deleteEmp(int id);
}
