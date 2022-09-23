package com.emp.Service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.emp.Repository.EmployeeRepo;
import com.emp.model.Employee;


@ApplicationScoped
public class EmployeeService {

	private final EmployeeRepo repo;
	
	@Inject
	public EmployeeService(EmployeeRepo repo) {
		this.repo=repo;
	}
	
	public Employee save(Employee employee) {
		return repo.saveEmp(employee);
	}
	
	public Employee getEmp(int id) {
		return repo.getEmp(id);
	}
	
	public boolean updateEmp(Employee employee) {
		return repo.updateEmp(employee);
	}
	
	public boolean deleteEmp(int id) {
		return repo.deleteEmp(id);
	}
}
