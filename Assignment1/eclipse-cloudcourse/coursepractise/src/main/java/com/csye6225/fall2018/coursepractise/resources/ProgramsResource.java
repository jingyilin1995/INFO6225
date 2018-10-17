package com.csye6225.fall2018.coursepractise.resources;

import java.util.ArrayList;
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

import com.csye6225.fall2018.coursepractise.datamodel.Course;
import com.csye6225.fall2018.coursepractise.datamodel.Professor;
import com.csye6225.fall2018.coursepractise.datamodel.Program;
import com.csye6225.fall2018.coursepractise.datamodel.Student;
import com.csye6225.fall2018.coursepractise.service.ProgramsService;

@Path("programs")
public class ProgramsResource {
	
	private static final Professor Professor = null;
	ProgramsService progService = new ProgramsService();
	
	@GET
	@Path("/allprograms")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Program> getAllPrograms(){
		return progService.getAllPrograms();
	}
	
	@GET
	@Path("/{programId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Program getProgram(@PathParam("programId") long progId) {
		return progService.getProgram(progId);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Program getProgramByname(@QueryParam("program") String program) {
	//	String name = "IS";
	//	List<Course> courselist = new ArrayList<Course>();
	//	List<Student> studentlist = new ArrayList<Student>();
	//	Course cour = new Course();
	//	Student stud = new Student();
	//	stud.setName("Felix");
	//	cour.setCourseId("CYSE1234");
	//	studentlist.add(stud);
	//	courselist.add(cour);
	//	progService.addProgram(name, studentlist, courselist);
		return progService.getProgramsByName(program);
	}
	
	//Get all courses of a program(by programId)
	@GET
	@Path("/{programId}/courses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getAllCoursesOfaProgram(@PathParam("programId") long programId){
		return progService.getAllCoursesOfProg(programId);
	}
	
	//Get all students of a program(by programId)
	@GET
	@Path("/{programId}/students")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getAllStudentsOfaProgram(@PathParam("programId") long programId){
		return progService.getAllStudentsOfProg(programId);
	}
	
	@DELETE
	@Path("/{programId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Program deleteProgram(@PathParam("programId") long progId) {
		return progService.deleteProgram(progId);
	}
	
	//delete a course of a program
	@DELETE
	@Path("/{programId}/courses")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Course> deleteAcourseOfProg(@PathParam("programId") long programId, Course cour){
		return progService.deleteACourseOfProg(programId, cour.getCourseId());
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program addProgram(Program prog) {
		return progService.addProgram(prog);
	}
	
	@PUT
	@Path("/{programId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program updateProgram(@PathParam("programId") long progId, Program prog) {
		return progService.updateProgramInformation(progId, prog);
	}
	
	@POST
	@Path("/{programId}/courses")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Course> addCouseToProg(@PathParam("programId") long progId, Course cour){
		return progService.addACourseToProg(progId, cour);
	}
}
