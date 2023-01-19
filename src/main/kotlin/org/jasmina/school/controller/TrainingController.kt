package org.jasmina.school.controller
import org.jasmina.school.model.Training
import org.jasmina.school.service.TrainingService
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/trainings")
class TrainingController(var trainingService: TrainingService) {

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