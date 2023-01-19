package org.jasmina.school.unittests.controller

import io.mockk.every
import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.junit.mockito.InjectMock
import org.assertj.core.api.Assertions.assertThat
import org.jasmina.school.controller.ParticipantController
import org.jasmina.school.model.Participant
import org.jasmina.school.model.Training
import org.jasmina.school.service.ParticipantService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import javax.inject.Inject
import javax.ws.rs.core.Response


@QuarkusTest
class ParticipantControllerMockitoTest {

    @InjectMock
    private lateinit var participantService: ParticipantService

    @Inject
    private lateinit var participantsResource: ParticipantController


    private val participants: List<Participant> = listOf(
        Participant(1, "username1", "firstname2", "lastname3", mutableListOf()),
        Participant(2, "username1", "firstname2", "lastname3", mutableListOf())
    )
    private val participant: Participant = Participant(3, "username", "firstname", "lastname", mutableListOf())

    private val training: Training = Training(2, "Unit Testing", "Test development", mutableListOf())


    @Test
    fun `test get all participants`() {

        Mockito.`when`(participantService.getAllParticipants()).thenReturn(participants)
        var response: Response = participantsResource.getAllParticipants()
        assertNotNull(response)
        val entity: List<Participant> = response.entity as List<Participant>
        assertEquals(entity, participants)
        assertEquals(1L, entity[0].participantsId);
        assertEquals(2L, entity[1].participantsId)
        assertEquals(2, entity.size)
        assertEquals(Response.Status.OK.statusCode, response.status)
    }

    @Test
    fun `test add participant`(){
        //given
        Mockito.`when`(participantService.addParticipant(participant)).thenReturn(participant)
        //when
        val result = participantsResource.addParticipant(participant)
        //then
        assertEquals(result, participant)
        assertThat(result).isNotNull
        assertThat(result.participantsId).isEqualTo(3)
        assertThat(result.userName).isEqualTo("username")
    }


    @Test
    fun `test enroll participants to training`() {

        Mockito.`when`(participantService.enrollParticipantToTraining(participant.participantsId, training.trainingId))
            .thenReturn(participant)
        val response: Response = participantsResource.enrollParticipantToTraining(3, 2)
        val entity: Participant = response.entity as Participant
        assertThat(entity).isNotNull
        assertEquals(participant.trainings, entity.trainings)
        assertEquals(Response.Status.OK.statusCode, response.status)
    }

    @Test
    fun `test find by participant by id`() {
        Mockito.`when`(participantService.findParticipantById(3)).thenReturn(participant)
        val response: Response = participantsResource.findParticipantById(3)
        val entity: Participant = response.entity as Participant
        assertNotNull(entity)
        assertEquals(entity.participantsId, participant.participantsId)
        assertEquals(entity, participant)
        assertEquals(Response.Status.OK.statusCode, response.status)
    }

    @Test
    fun `test find by username`() {
        Mockito.`when`(participantService.findParticipantByUserName("username")).thenReturn(participants)
        val response: Response = participantsResource.findByUsername("username")
        val entity: List<Participant> = response.entity as List<Participant>
        assertNotNull(entity)
        assertEquals(entity[1].participantsId, participants[1].participantsId)
        assertEquals(entity[1], participants[1])
        assertEquals(Response.Status.OK.statusCode, response.status)

    }

    @Test
    fun `test edit participant`() {
        Mockito.`when`(participantService.editParticipantById(3, participant)).thenReturn(participant)
        val response: Response = participantsResource.editParticipant(3, participant)
        val entity: Participant = response.entity as Participant
        assertNotNull(entity)
        assertEquals(entity.participantsId, participant.participantsId)
        assertEquals(entity, participant)
        assertEquals(Response.Status.OK.statusCode, response.status)
    }

    @Test
    @Disabled
    fun `test delete participant`() {
        //function needs to be implemented
    }

}