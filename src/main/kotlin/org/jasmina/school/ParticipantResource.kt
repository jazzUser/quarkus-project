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
        var participant = participantService.getAllParticipants()
        return Response.ok(participant).status(Response.Status.CREATED).build()
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
        @PathParam("trainingId") participantId: Long,
        @PathParam("participantId") trainingId: Long,
    ): Response {
        participantService.addParticipantToTraining(participantId, trainingId)
        return Response.ok().status(200).build()
    }
}