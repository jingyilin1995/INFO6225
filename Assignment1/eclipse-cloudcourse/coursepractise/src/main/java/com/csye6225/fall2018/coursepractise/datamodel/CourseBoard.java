package com.csye6225.fall2018.coursepractise.datamodel;

public class CourseBoard {
	
	private long boardId;
	private String courseId;
	
	public CourseBoard() {
		
	}
	
	public CourseBoard(long boardId, String courseId) {
		this.setBoardId(boardId);
		this.setCourseId(courseId);
	}

	public long getBoardId() {
		return boardId;
	}

	public void setBoardId(long boardId) {
		this.boardId = boardId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
}
