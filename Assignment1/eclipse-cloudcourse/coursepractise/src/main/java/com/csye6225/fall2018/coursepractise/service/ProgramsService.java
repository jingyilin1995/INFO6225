package com.csye6225.fall2018.coursepractise.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.coursepractise.datamodel.Course;
import com.csye6225.fall2018.coursepractise.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.coursepractise.datamodel.Program;
import com.csye6225.fall2018.coursepractise.datamodel.Student;

public class ProgramsService {
	static HashMap<Long, Program> prog_Map = InMemoryDatabase.getProgramDB();
	
	//GET"...webapi/programs
	public List<Program> getAllPrograms(){
		ArrayList<Program> list =new ArrayList<>();
		for(Program prog : prog_Map.values()) {
			list.add(prog);
		}
		return list;
	}
	
	//adding a program
	public void addProgram(String name, List<Student> students, List<Course> courses) {
		//next id
		long nextAvailableId = prog_Map.size() + 1;
		
		Program prog = new Program(nextAvailableId, name, students, courses);
		prog_Map.put(nextAvailableId, prog);
	}
	
	public Program addProgram(Program prog) {
		long nextAvailableId = prog_Map.size() + 1;
		prog.setProgramId(nextAvailableId);
		prog_Map.put(nextAvailableId, prog);
		return prog_Map.get(nextAvailableId);
	}
	
	//Getting one program
	public Program getProgram(Long progId) {
		return prog_Map.get(progId);
	}
	
	//Deleteing a program
	public Program deleteProgram(Long progId) {
		Program deletedProgDetails = prog_Map.get(progId);
		prog_Map.remove(progId);
		return deletedProgDetails;
	}
	
	//Updating program info
	public Program updateProgramInformation(Long progId, Program prog) {
		Program oldProgObject = prog_Map.get(progId);
		progId = oldProgObject.getProgramId();
		prog.setProgramId(progId);
		prog_Map.put(progId, prog);
		return prog;
	}
	
	//Add a course to a program
	public List<Course> addACourseToProg(Long progId, Course cour){
		List<Course> oldlist = prog_Map.get(progId).getCourses();
		Program oldprog = prog_Map.get(progId);
		oldlist.add(cour);
		oldprog.setCourses(oldlist);
		prog_Map.put(progId, oldprog);
		return prog_Map.get(progId).getCourses();			
	}
	
	//Get programs of a particular name
	public Program getProgramsByName(String program){
		for(Program prog : prog_Map.values()) {
			if(prog.getProgramName().equals(program)) {
				return prog;
			}
		}
		return null;
	}
	
	//Get all courses of a program
	public List<Course> getAllCoursesOfProg(Long programId){
		return prog_Map.get(programId).getCourses();
	}
	
	//Get all students of a program
	public List<Student> getAllStudentsOfProg(Long programId){
		return prog_Map.get(programId).getStudents();
	}
	
	//Delete a course of a program
	public List<Course> deleteACourseOfProg(Long programId, String courseId){
		Program oldprog = prog_Map.get(programId);
		List<Course> newlist = new ArrayList<Course>();
		for(int i =0; i<oldprog.getCourses().size();i++) {
			if(!oldprog.getCourses().get(i).getCourseId().equals(courseId)) {
				newlist.add(oldprog.getCourses().get(i));
			}
		}
		oldprog.setCourses(newlist);
		prog_Map.put(programId, oldprog);
		return prog_Map.get(programId).getCourses();
		
	}
	
}
