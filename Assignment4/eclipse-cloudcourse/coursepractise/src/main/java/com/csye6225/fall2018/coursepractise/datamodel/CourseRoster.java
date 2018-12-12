package com.csye6225.fall2018.coursepractise.datamodel;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "courseRoster")
public class CourseRoster {
	
	private String Id;
	private long rosterId;
	private String courseId;
	private List<String> rosterlist;
	
	public CourseRoster() {
		
	}
	
	public CourseRoster(long rosterId, String courseId, List<String> rosterlist) {
		this.setRosterId(rosterId);
		this.setCourseId(courseId);
		this.setRosterlist(rosterlist);
	}

	@DynamoDBIndexHashKey(attributeName = "rosterId",globalSecondaryIndexName = "rosterId-index")
	public long getRosterId() {
		return rosterId;
	}

	public void setRosterId(long rosterId) {
		this.rosterId = rosterId;
	}

	@DynamoDBAttribute(attributeName = "courseId")
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@DynamoDBAttribute(attributeName = "rosterlist")
	public List<String> getRosterlist() {
		return rosterlist;
	}

	public void setRosterlist(List<String> rosterlist) {
		this.rosterlist = rosterlist;
	}

	@DynamoDBHashKey(attributeName = "Id")
	@DynamoDBAutoGeneratedKey
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

}