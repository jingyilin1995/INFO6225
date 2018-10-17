package com.csye6225.fall2018.coursepractise.datamodel;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

public class Course {
	private String courseId;
	private Student studentTA;
	private Professor associatedProfessor;
	private List<Lecture> lectures;
	private CourseRoster roster;
	private CourseBoard board;
	
	public Course() {
		
	}
	public Course(String courseId, Student studentTA, Professor accociatedProfessor, List<Lecture> lectures, CourseBoard board, CourseRoster roster ) {
		this.setCourseId(courseId);
		this.setStudentTA(studentTA);
		this.setAssociatedProfessor(accociatedProfessor);
		this.setRoster(roster);
		this.setBoard(board);
		this.setLectures(lectures);
		
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public Student getStudentTA() {
		return studentTA;
	}
	public void setStudentTA(Student studentTA) {
		this.studentTA = studentTA;
	}
	public Professor getAssociatedProfessor() {
		return associatedProfessor;
	}
	public void setAssociatedProfessor(Professor associatedProfessor) {
		this.associatedProfessor = associatedProfessor;
	}
	public CourseRoster getRoster() {
		return roster;
	}
	public void setRoster(CourseRoster roster) {
		this.roster = roster;
	}
	public CourseBoard getBoard() {
		return board;
	}
	public void setBoard(CourseBoard board) {
		this.board = board;
	}
	public List<Lecture> getLectures() {
		return lectures;
	}
	public void setLectures(List<Lecture> lectures) {
		this.lectures = lectures;
	}

}
