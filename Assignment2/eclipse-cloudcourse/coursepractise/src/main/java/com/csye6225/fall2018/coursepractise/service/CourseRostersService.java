package com.csye6225.fall2018.coursepractise.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.coursepractise.datamodel.Course;
import com.csye6225.fall2018.coursepractise.datamodel.CourseBoard;
import com.csye6225.fall2018.coursepractise.datamodel.CourseRoster;
import com.csye6225.fall2018.coursepractise.datamodel.DynamoDbConnector;
import com.csye6225.fall2018.coursepractise.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.coursepractise.datamodel.Student;

public class CourseRostersService {
	static HashMap<Long,CourseRoster> roster_Map = InMemoryDatabase.getCourseRosterDB();
	static HashMap<String, Course> cour_Map = InMemoryDatabase.getCourseDB();
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper;
	
	public CourseRostersService() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	//adding a roster	
	public CourseRoster addCourseRoster(long rosterId, String courseId) {
		CourseRoster roster = new CourseRoster();
		roster.setRosterId(rosterId);
		roster.setCourseId(courseId);
		roster.setRosterlist(new ArrayList<String>());
		mapper.save(roster);
		return mapper.load(CourseRoster.class, roster.getId());
	}
	
	//getting a roster by courseId
	public CourseRoster getRoster(String courseId) {
//		for (CourseRoster roster : roster_Map.values()) {
//			if(roster.getCourseId().equals(courseId)) {
//				return roster;
//			}
//		}
//		return new CourseRoster();
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val2", new AttributeValue().withS(courseId));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("courseId = :val2").withExpressionAttributeValues(eav);
		List<CourseRoster> scanResult = mapper.scan(CourseRoster.class, scanExpression);
		if(scanResult.size() != 0)
		{
			return scanResult.get(0);
		}
		return null;
	}
	
	//clear a roster's rosterlist
	public CourseRoster clearRoster(String courseId) {
/*		for (CourseRoster roster : roster_Map.values()) {
			if(roster.getCourseId().equals(courseId)) {
				roster.getRosterlist().clear();
				return roster;
			}
		}
		return new CourseRoster();
*/
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val2", new AttributeValue().withS(courseId));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("courseId = :val2").withExpressionAttributeValues(eav);
		List<CourseRoster> scanResult = mapper.scan(CourseRoster.class, scanExpression);
		CourseRoster roster = new CourseRoster();
		if(scanResult.size() != 0)
		{
			roster = scanResult.get(0);
			roster.setRosterlist(new ArrayList<String>());
			mapper.save(roster);
			return mapper.load(CourseRoster.class,roster.getId());
		}
		else
			return null;
	}
	
	//delete a roster: used when deleting a course
	public void deleteRoster(long rosterId) {
/*		for(CourseRoster roster : roster_Map.values()) {
			if(roster.getCourseId().equals(courseId)) {
				roster_Map.remove(roster.getRosterId());
			}
			break;//one course can only have one roster
		}
*/
		String rosterIds = String.valueOf(rosterId);
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val2", new AttributeValue().withN(rosterIds));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("rosterId = :val2").withExpressionAttributeValues(eav);
		List<CourseRoster> scanResult;
		scanResult = mapper.scan(CourseRoster.class, scanExpression);
		if(scanResult.size() != 0)
		{
			mapper.delete(scanResult.get(0));
		}
	}
	
	//add a new student into roster of a course
	public CourseRoster updateRosterAddStudent(String courseId, Student student) {		
//		CourseRoster updateRoster = new CourseRoster();
//		for(CourseRoster roster : roster_Map.values()) {
//			if(roster.getCourseId().equals(courseId)) {
//				roster.getRosterlist().add(student);
//				updateRoster = roster;
//			}
//		}
//		return updateRoster;
		String studentId = student.getStudentId();
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val2", new AttributeValue().withS(courseId));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("courseId = :val2").withExpressionAttributeValues(eav);
		List<CourseRoster> scanResult = mapper.scan(CourseRoster.class, scanExpression);
		CourseRoster roster = new CourseRoster();
		if(scanResult.size() != 0)
		{
			roster = scanResult.get(0);
			roster.getRosterlist().add(studentId);
			mapper.save(roster);
			return mapper.load(CourseRoster.class,roster.getId());
		}
		else
			return null;
		
		
	}
	
	//delete a student from a roster of a course
	public CourseRoster updateRosterDeleteStudent(String courseId, Student student) {
//		CourseRoster updateRoster = new CourseRoster();
//		for(CourseRoster roster : roster_Map.values()) {
//			if(roster.getCourseId().equals(courseId)) {
//				for( int i =0; i < roster.getRosterlist().size(); i++)
//				{
//					if(roster.getRosterlist().get(i).getStudentId() == student.getStudentId()){
//						roster.getRosterlist().remove(i);
//					}
//				}
//				updateRoster = roster;
//			}
//		}
//		for(Course cour : cour_Map.values()) {
//			if(cour.getCourseId().equals(courseId)) {
//				cour.setRoster(updateRoster);
//				cour_Map.put(courseId, cour);
//			}
//		}
//		return updateRoster;
		String studentId = student.getStudentId();
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val2", new AttributeValue().withS(courseId));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("courseId = :val2").withExpressionAttributeValues(eav);
		List<CourseRoster> scanResult = mapper.scan(CourseRoster.class, scanExpression);
		CourseRoster roster = new CourseRoster();
		if(scanResult.size() != 0)
		{
			roster = scanResult.get(0);
			roster.getRosterlist().remove(studentId);
			mapper.save(roster);
			return mapper.load(CourseRoster.class,roster.getId());
		}
		else
			return null;
	}
}
 