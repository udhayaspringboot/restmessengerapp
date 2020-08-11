package com.messengerapprestapi.controller;

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

import com.messengerapprestapi.model.Message;
import com.messengerapprestapi.model.Profile;
import com.messengerapprestapi.service.ProfileService;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {
	
	ProfileService msd = new ProfileService();

	@GET
	public List<Profile> getProf() {
		return msd.getAllProf();
	}

	@GET
	@Path("/{profName}")
	public Profile getMessId(@PathParam("profName") String profName) {
		Profile pro = msd.getProf(profName);

		return pro;

	}

	@POST
	public String addMess(Profile profile) {

		System.out.println("entering controller");
		msd.saveProf(profile);

		return "Profile Addedd successfully";

	}

	@PUT
	@Path("/{profName}")
	public String updateMess(@PathParam("profName") String profName, Profile profile) {
	Profile pro = msd.getProf(profName);

		// System.out.println("date in");
		profile.setProfileName(profName);

		profile.setCreated(pro.getCreated());

		msd.updateProf(profile);

		return "Profile Updated successfully";

	}

	@DELETE
	@Path("/{profName}")
	public String delMessId(@PathParam("profName") String profName) {
		msd.deleProf(profName);

		return "Profile Deleted";

	}

	

}
