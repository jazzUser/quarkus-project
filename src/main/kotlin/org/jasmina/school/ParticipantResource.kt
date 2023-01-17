package org.jasmina.school

import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Path("/participants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class ParticipantResource(var participantService: ParticipantService) {

    @GET
    fun getAllParticipants(): Response {
        val participants = participantService.getAllParticipants()
        return Response.ok(participants).status(Response.Status.CREATED).build()
    }
    @POST
    @Path("/add")
    @Transactional
    fun addParticipant(participant: Participant): Response{
    participantService.addParticipant(participant)
    return Response.ok(participant).status(Response.Status.CREATED).build()
    }

    @Path("/enroll/{participantId}/{trainingId}")
    @PUT
    @Transactional
    fun enrollParticipantToTraining(
        @PathParam("participantId") participantId: Long,
        @PathParam("trainingId") trainingId: Long,
    ): Response {
        participantService.enrollParticipantToTraining(participantId, trainingId)
        return Response.ok().status(200).build()
    }

   @GET
   @Path("{participantId}")
    fun findParticipantById(@PathParam("participantId") participantId: Long): Response {
       val participantsId = participantService.findParticipantById(participantId)
       return Response.ok(participantsId).build()
   }

    @GET
    @Path("/username/{username}")
    fun findByCategory(@PathParam("username") username:String): Response {
        val usernames  =  participantService.getParticipantByUserName(username)
        return Response.ok(usernames).build()

    }

//needs improvement
    @PUT
    @Path("edit/{participantId}")
    @Transactional
    fun updateParticipant(@PathParam("participantId") participantId: Long, participantToUpdate: Participant): Response {
    participantService.editParticipantById(participantId, participantToUpdate)
    return Response.ok().status(200).build()
    }
}