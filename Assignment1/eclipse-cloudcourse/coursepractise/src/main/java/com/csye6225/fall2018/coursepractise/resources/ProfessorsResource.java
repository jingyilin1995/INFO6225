package com.csye6225.fall2018.coursepractise.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.coursepractise.datamodel.Professor;
import com.csye6225.fall2018.coursepractise.service.ProfessorsService;

// .. /webapi/myresource
@Path("professors")
public class ProfessorsResource {

	ProfessorsService profService = new ProfessorsService();
	
	@GET
	@Path("/allprofessors")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> getAllProfessors(){
		String name = "Sam";
		String department = "Information Systems";
		Date date = new Date();
		addProfessor(name,department,date);
		return profService.getAllProfessors();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> getProfessorsByDeparment(
			@QueryParam("department") String department			) {
		
		if (department == null) {
			return profService.getAllProfessors();
		}
		return profService.getProfessorsByDepartment(department);
		
	}
	
	// ... webapi/professor/1 
	@GET
	@Path("/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor getProfessor(@PathParam("professorId") long profId) {
		return profService.getProfessor(profId);
	}
	
	@DELETE
	@Path("/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor deleteProfessor(@PathParam("professorId") long profId) {
		return profService.deleteProfessor(profId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Professor addProfessor(Professor prof) {
			return profService.addProfessor(prof);
	}
	
	@PUT
	@Path("/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Professor updateProfessor(@PathParam("professorId") long profId, 
			Professor prof) {
		return profService.updateProfessorInformation(profId, prof);
	}
	
	public void addProfessor(String name, String department, Date joiningDate) {
		profService.addProfessor(name, department, joiningDate);
	}
 }

