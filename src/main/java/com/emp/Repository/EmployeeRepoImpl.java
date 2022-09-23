package com.emp.Repository;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;

import com.emp.model.Employee;

@ApplicationScoped
public class EmployeeRepoImpl implements EmployeeRepo {
	
	@Inject
	DataSource ds;

	@Override
	public Employee saveEmp(Employee employee) {
		Connection conn = null;
		String sql = "insert into employee(id,name,designation,address) values(?,?,?,?)";
		try {
			conn = ds.getConnection();
			PreparedStatement psms =conn.prepareStatement(sql);
			psms.setInt(1, employee.getId());
			psms.setString(2, employee.getName());
			psms.setString(3, employee.getDesignation());
			psms.setString(4,employee.getAddress());
			 int affectedRows = psms.executeUpdate();
			 System.out.println(affectedRows);
		} catch (SQLException ex) {
            System.out.println(ex.getMessage());
		}
				
		return employee;
	}

	@Override
	public Employee getEmp(int id) {
		Employee employee = null;
		try(Connection conn = ds.getConnection();
				PreparedStatement ps= conn.prepareStatement("select id, name, designation, address from employee where id=?");) {
			ps.setInt(1, id);
			System.out.println(ps);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				String designation = rs.getString("designation");
				String address = rs.getString("address");
				employee = new Employee(id,name,designation,address);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			    
		
		return employee;
	}

	@Override
	public boolean updateEmp(Employee employee) {
		boolean rowUpdated = false;
		try(Connection conn= ds.getConnection(); 
				PreparedStatement ps = conn.prepareStatement("update employee set name=?, designation=?, address=? where id=?")) {
			ps.setString(1, employee.getName());
			ps.setString(2, employee.getDesignation());
			ps.setString(3, employee.getAddress());
			ps.setInt(4, employee.getId());
			rowUpdated = ps.executeUpdate()>0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowUpdated;
	}

	@Override
	public boolean deleteEmp(int id) {
		boolean rowUpdated = false;
		
		try(Connection conn = ds.getConnection(); 
				PreparedStatement ps = conn.prepareStatement("delete from employee where id=?");) {
			ps.setInt(1, id);
			rowUpdated = ps.executeUpdate()>0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rowUpdated;
	}

	
}
