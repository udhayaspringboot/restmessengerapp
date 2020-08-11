package com.messengerapprestapi.controller;

import java.util.ArrayList;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.messengerapprestapi.model.Comment;
import com.messengerapprestapi.service.CommentServicce;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {
	
	
	CommentServicce cServ = new CommentServicce();
	
	/*
	 * @GET public String getCom() { return "Hi Command"; }
	 */
	/*
	 * @GET
	 * 
	 * @Path("/{commentId}") public String getCom(@PathParam("messageId") int
	 * messId,@PathParam("commentId") int id) { return "Hi Command value "+id +
	 * " messId "+messId; }
	 */
	
	@POST
	public Response saveCom(Comment comment)
	{
		if(comment == null)
		{
			return Response.status(Status.NOT_FOUND).entity("Enter correct details ").build();
			
		}else {
		
		cServ.saveCom(comment);
		
		return Response.status(Status.OK).entity("Saved comment ssuccessfully").build();}
	}
	
	@GET

	public Response getComms(@PathParam("messageId") int messId)
	{
		
		System.out.println("mess id at comment controller"+messId);
		List<Comment> lisCom = new ArrayList<Comment>();
		lisCom=cServ.getAllComms(messId);
		if(lisCom == null)
		{
			return Response.status(Status.NOT_FOUND).entity("no data available for this id "+messId).build();
		}else
		{
		return
		Response.status(Status.OK).entity(lisCom ).build();
		}
	}
	
	@PUT
	@Path("/{commentId}")
	public Response updateComm(@PathParam("messageId") int messageId,@PathParam("commentId") int commentid,Comment comment)
	{
		List<Comment> cdf = cServ.getAllComms(messageId);
		
		if(cdf == null)
		{
			return Response.status(Status.NOT_FOUND).entity("Enter correct details ").build();
		}
		else {
		for (Comment comment2 : cdf) {
			
			if(commentid == comment2.getCommentId())
			{
				comment.setComCreated(comment2.getComCreated());
			}
		}
		
		comment.setCommentId(commentid);
		
		cServ.updateComm(comment);
		
		
		return Response.status(Status.OK).entity( "Updated successfully").build();
		
	}}
	
	@DELETE
	@Path("/{commentId}")
	public Response deleComm(@PathParam("messageId") int messageId,@PathParam("commentId") int commentid)
	{
		
		if(messageId>0 && commentid>0)
		{
		cServ.deleComm(messageId, commentid);
		
		return Response.status(Status.OK).entity( "Deleted Successfully").build();
		}
		else
		{
			return Response.status(Status.NOT_FOUND).entity("Enter correct details ").build();
		}
	
	}
	
}
