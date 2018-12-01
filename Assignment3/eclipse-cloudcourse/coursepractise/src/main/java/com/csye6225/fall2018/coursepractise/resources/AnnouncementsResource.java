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

import com.csye6225.fall2018.coursepractise.datamodel.Announcement;
import com.csye6225.fall2018.coursepractise.service.AnnouncementsService;

@Path("announcements")
public class AnnouncementsResource {

	AnnouncementsService annService = new AnnouncementsService();
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Announcement addAnnouncement(Announcement ann) {
		return annService.addAnnouncement(ann);
	}
	
	@GET
	@Path("/allannouncements")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Announcement> getAllAnnouncements(){
		return annService.getAllAnnouncements();
	}
	
	//get a announcement
	@GET
	@Path("/{boardId}_{annId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Announcement getAAnnouncement(@PathParam("annId") String annId, @PathParam("boardId") long boardId) {
		return annService.getAAnnouncement(annId,boardId);
	}
	
	//get announcements by boardId
	@GET
	@Path("/{boardId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Announcement> getAnnbyBoardId(@PathParam("boardId") long boardId){
		return annService.getAnnbyBoardId(boardId);
	}
	
	//delete a announcement
	@DELETE
	@Path("/{boardId}_{annId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Announcement deleteAnn(@PathParam("annId") String annId, @PathParam("boardId") long boardId) {
		return annService.deleteAnn(annId, boardId);
	}
	
	//update a announcement
	@PUT
	@Path("/{boardId}_{annId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Announcement updateAAnnouncement(@PathParam("annId") String annId, @PathParam("boardId") long boardId,Announcement ann) {
		return annService.updateAnn(annId, boardId, ann);
	}
}


