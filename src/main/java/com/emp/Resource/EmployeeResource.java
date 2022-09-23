package com.emp.Resource;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import static javax.ws.rs.core.Response.ok;

import com.emp.Service.EmployeeService;
import com.emp.model.Employee;

@Path("/emp")
@RequestScoped
public class EmployeeResource {

	
	private final EmployeeService service;
	
	@Context
	UriInfo uriInfo;

	
	@Context
	ResourceContext resourceContext;
	
	@Inject
	public EmployeeResource(EmployeeService service) {
		this.service=service;
	}
	
	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveEmp(@RequestBody Employee employee) {
		return ok(this.service.save(employee)).build();
	}
	
	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Employee getEmp(@PathParam("id") int id) {
		return this.service.getEmp(id);
	}
	
	@Path("/update")
	@PUT
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean updateEmp(@RequestBody Employee employee) {
		System.out.println(employee);
		return this.service.updateEmp(employee);
	}
	
	@Path("/delete/{id}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deleteEmp(@PathParam("id") int id) {
		return this.service.deleteEmp(id);
	}
	
	
}
