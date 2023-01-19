package org.jasmina.school.controller

import org.jasmina.school.model.Participant
import org.jasmina.school.service.ParticipantService
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Path("v1/api/participants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class ParticipantController(var participantService: ParticipantService) {

    @GET
    @Path("/all")
    fun getAllParticipants(): Response {
        val participant = participantService.getAllParticipants()
        return Response.ok(participant).build()
    }

    @POST
    @Path("/add")
    @Transactional
    fun addParticipant(participant: Participant): Participant {
    return participantService.addParticipant(participant)
    }

    @Path("/enroll/{participantId}/{trainingId}")
    @PUT
    @Transactional
    fun enrollParticipantToTraining(
        @PathParam("participantId") participantId: Long,
        @PathParam("trainingId") trainingId: Long,
    ): Response {
        val participant = participantService.enrollParticipantToTraining(participantId, trainingId)
        return Response.ok(participant).status(200).build()
    }

   @GET
   @Path("{participantId}")
    fun findParticipantById(@PathParam("participantId") participantId: Long): Response {
       val participantsId = participantService.findParticipantById(participantId)
       return Response.ok(participantsId).build()
   }

    @GET
    @Path("/username/{username}")
    fun findByUsername(@PathParam("username") username:String): Response {
        val usernames  =  participantService.findParticipantByUserName(username)
        return Response.ok(usernames).build()

    }

    @PUT
    @Path("edit/{participantId}")
    @Transactional
    fun editParticipant(@PathParam("participantId") participantId: Long, participantToUpdate: Participant): Response {
    val participant = participantService.editParticipantById(participantId, participantToUpdate)
    return Response.ok(participant).status(200).build()
    }
}