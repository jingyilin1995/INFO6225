package com.csye6225.fall2018.coursepractise.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.coursepractise.datamodel.Course;
import com.csye6225.fall2018.coursepractise.datamodel.DynamoDbConnector;
import com.csye6225.fall2018.coursepractise.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.coursepractise.datamodel.Program;
import com.csye6225.fall2018.coursepractise.datamodel.Student;

public class ProgramsService {
	static HashMap<Long, Program> prog_Map = InMemoryDatabase.getProgramDB();
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper;
	
	public ProgramsService() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	//GET"...webapi/programs
	public List<Program> getAllPrograms(){
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		List<Program> scanResult = mapper.scan(Program.class, scanExpression);
		return scanResult;
	}
	
	//adding a program
	public void addProgram(String programId, String name, List<String> students, List<String> courses) {
		//next id
		Program prog = new Program(programId, name, students, courses);
		mapper.save(prog);
	}
	
	public Program addProgram(Program prog) {
		mapper.save(prog);
		return mapper.load(Program.class, prog.getId());
		
	}
	
	//Getting one program
	public Program getProgram(String progId) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val2", new AttributeValue().withS(progId));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("programId = :val2").withExpressionAttributeValues(eav);
		List<Program> scanResult = mapper.scan(Program.class, scanExpression);
		if(scanResult.size()!=0)
		{
			return scanResult.get(0);
		}
		return null;
	}
	
	//Deleteing a program
	public Program deleteProgram(String progId) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val2", new AttributeValue().withS(progId));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("programId = :val2").withExpressionAttributeValues(eav);
		List<Program> scanResult = mapper.scan(Program.class, scanExpression);
		if(scanResult.size()!=0)
		{
			Program deletedProgDetails = scanResult.get(0);
			mapper.delete(deletedProgDetails);
			return deletedProgDetails;
		}
		return null;
	}
	
	//Updating program info
	public Program updateProgramInformation(String programId, Program prog) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val2", new AttributeValue().withS(programId));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("programId = :val2").withExpressionAttributeValues(eav);
		List<Program> scanResult = mapper.scan(Program.class, scanExpression);
		if(scanResult.size()!=0)
		{
			String Id = scanResult.get(0).getId();
			prog.setId(Id);
			mapper.save(prog);
			return mapper.load(Program.class, prog.getId());
		}
		return null;
	}
	
	//Add a course to a program
	public Program addACourseToProg(String programId, Course cour){
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val2", new AttributeValue().withS(programId));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("programId = :val2").withExpressionAttributeValues(eav);
		List<Program> scanResult = mapper.scan(Program.class, scanExpression);
		if(scanResult.size()!=0)
		{
			Program program = scanResult.get(0);
			List<String> courselist = program.getCourses();
			courselist.add(cour.getCourseId());
			program.setCourses(courselist);
			mapper.save(program);
			return mapper.load(Program.class, program.getId());
		}
		return null;
	}
	
	//Get programs of a particular name
	public Program getProgramsByName(String programName){
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val2", new AttributeValue().withS(programName));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("programName = :val2").withExpressionAttributeValues(eav);
		List<Program> scanResult = mapper.scan(Program.class, scanExpression);
		if(scanResult.size()!=0)
		{
			return scanResult.get(0);
		}
		return null;
	}
	
	//Get all courses of a program
	public List<Course> getAllCoursesOfProg(String programId){
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val2", new AttributeValue().withS(programId));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("programId = :val2").withExpressionAttributeValues(eav);
		List<Program> scanResult = mapper.scan(Program.class, scanExpression);
		if(scanResult.size()!=0)
		{
			Program program = scanResult.get(0);
			String programName = program.getProgramName();
			Map<String, AttributeValue> eav2 = new HashMap<String, AttributeValue>();
			eav2.put(":val3", new AttributeValue().withS(programName));
			DynamoDBScanExpression scanExpression2 = new DynamoDBScanExpression()
					.withFilterExpression("department = :val3").withExpressionAttributeValues(eav2);
			List<Course> scanResult2 = mapper.scan(Course.class, scanExpression2);
			return scanResult2;
		}
		return null;
	}
	
	//Get all students of a program
	public List<Student> getAllStudentsOfProg(String programId){
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val2", new AttributeValue().withS(programId));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("programId = :val2").withExpressionAttributeValues(eav);
		List<Program> scanResult = mapper.scan(Program.class, scanExpression);
		if(scanResult.size()!=0)
		{
			Program program = scanResult.get(0);
			String programName = program.getProgramName();
			Map<String, AttributeValue> eav2 = new HashMap<String, AttributeValue>();
			eav2.put(":val3", new AttributeValue().withS(programName));
			DynamoDBScanExpression scanExpression2 = new DynamoDBScanExpression()
					.withFilterExpression("department = :val3").withExpressionAttributeValues(eav2);
			List<Student> scanResult2 = mapper.scan(Student.class, scanExpression2);
			return scanResult2;
		}
		return null;
	}
	
	//Delete a course of a program
	public Program deleteACourseOfProg(String programId, String courseId){
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val2", new AttributeValue().withS(programId));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("programId = :val2").withExpressionAttributeValues(eav);
		List<Program> scanResult = mapper.scan(Program.class, scanExpression);
		if(scanResult.size()!=0)
		{
			Program program = scanResult.get(0);
			List<String> courselist = program.getCourses();
			courselist.remove(courseId);
			program.setCourses(courselist);
			mapper.save(program);
			return mapper.load(Program.class, program.getId());
		}
		return null;
	}
	
}
