package org.jasmina.school
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/trainings")
class TrainingResource(var trainingService: TrainingService) {

    @GET
    fun getAllTrainings(): Response {
        var trainings = trainingService.getAllTrainings()
        return Response.ok(trainings).status(Response.Status.CREATED).build()
    }

    @POST
    @Path("/add")
    @Transactional
    fun addTraining(training: Training): Response{
        trainingService.addTraining(training)
        return Response.ok(training).status(Response.Status.CREATED).build()
    }

}