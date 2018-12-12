package com.csye6225.fall2018.coursepractise.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.coursepractise.datamodel.Course;
import com.csye6225.fall2018.coursepractise.datamodel.Lecture;
import com.csye6225.fall2018.coursepractise.service.LecturesService;

@Path("courses")
public class LecturesResource {
	LecturesService lectService = new LecturesService();
	
	@GET
	@Path("/alllectures")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Lecture> getAllLectures(){
//		List<String> notes = new ArrayList<String>();
//		List<String> materials = new ArrayList<String>();
//		String note1 = "This is the first lecture!";
//		String material1 ="http://icemoon.iteye.com/blog/717151";
//		notes.add(note1);
//		materials.add(material1);
//		addLecture(notes,materials);
		
		return lectService.getAllLectures();
	}
	
	@GET
	@Path("{courseId}/lectures")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Lecture> getLectureByCourse(@PathParam("courseId") String courseId) {
		return lectService.getAllLectures(courseId);
	}
	
	@DELETE
	@Path("{courseId}/lectures")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteLectureByCourse(@PathParam("courseId") String courseId, Lecture lecture){
		lectService.deleteLectureofaCourse(courseId, lecture.getLectureId());
	}
	
	@POST
	@Path("{courseId}/lectures")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Course addlectureToCourse(@PathParam("courseId") String courseId, Lecture lecture){
		return lectService.addLecture(courseId,lecture);
	}
	
	@PUT
	@Path("{courseId}/lectures")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updatelectureToCourse(@PathParam("courseId") String courseId, Lecture lecture){
		lectService.updateLecture(courseId, lecture);
	}
}