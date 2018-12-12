package com.csye6225.fall2018.assign4Registrar.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.assign4Registrar.datamodel.Registrar;
import com.csye6225.fall2018.assign4Registrar.service.RegistrarsService;

@Path("Registrars")
public class RegistrarsResource {

	RegistrarsService regiService = new RegistrarsService();
	
	@GET
	@Path("/allRegistrars")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Registrar> getAllRegistrars(){
		return regiService.getAllRegistrars();
	}
	
	@GET
	@Path("/{RegistrationId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Registrar getRegistrar(@PathParam("RegistrationId") String RegistrationId) {
		return regiService.getRegistrar(RegistrationId);
	}
	
	@POST
	@Path("/registerOffering")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Registrar addRegistrar(Registrar regi) {
		System.out.println(regi.getOfferingId());
		return regiService.addRegistrar(regi);
	}
	
	@DELETE
	@Path("/{RegistrationId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Registrar deleteRegistrar(@PathParam("RegistrationId") String RegistrationId ) {
		return regiService.deleteRegistrar(RegistrationId);
	}
	
}
