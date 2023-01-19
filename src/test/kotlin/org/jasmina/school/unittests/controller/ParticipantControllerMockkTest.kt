package org.jasmina.school.unittests.controller

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.jasmina.school.controller.ParticipantController
import org.jasmina.school.model.Participant
import org.jasmina.school.model.Training
import org.jasmina.school.service.ParticipantService
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.extension.ExtendWith
import javax.ws.rs.core.Response
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
class ParticipantControllerMockkTest {

    @MockK
    private lateinit var participantService: ParticipantService

    @InjectMockKs
    private lateinit var testUnderClass: ParticipantController

    private val participant: Participant = Participant(3, "username", "firstname", "lastname", mutableListOf())

    private val participants: List<Participant> = listOf(
        Participant(1, "username1", "firstname1", "lastname1", mutableListOf()),
        Participant(2, "username2", "firstname2", "lastname3", mutableListOf())
    )

    private val training: Training = Training(2, "Unit Testing", "Test development", mutableListOf())
    @Test
    fun `test get all participants`(){
        //given
        every { participantService.getAllParticipants() } returns participants
        //when
        val result: Response = testUnderClass.getAllParticipants()
        //then
        assertThat(result.entity).isEqualTo(participants)
        assertFalse(participants.isEmpty())
        assertEquals(participants[1].participantsId, 2)

    }

    @Test
    fun `test add participant`(){
        //given
        every { participantService.addParticipant(participant)} returns participant
        //when
        val result = testUnderClass.addParticipant(participant)
        //then
        assertEquals(result, participant)
        assertThat(result).isNotNull
        assertThat(result.participantsId).isEqualTo(3)
        assertThat(result.userName).isEqualTo("username")
    }

    @Test
    fun `test enroll participants to training`(){
        //given
        every { participantService.enrollParticipantToTraining(participant.participantsId, training.trainingId)} returns participant
        //when
        val result = testUnderClass.enrollParticipantToTraining(3,2)
        val entity: Participant = result.entity as Participant
        //then
        assertEquals(participant.trainings, entity.trainings)
        assertEquals(Response.Status.OK.statusCode, result.status)
        assertThat(result).isNotNull
        assertThat(result.entity).isEqualTo(participant)
    }

    @Test
    fun `test find by participant by id`(){
        //given
        every { participantService.findParticipantById(3)} returns participant
        //when
        val result = testUnderClass.findParticipantById(3)
        val entity: Participant = result.entity as Participant
        //then
        assertEquals(Response.Status.OK.statusCode, result.status)
        assertEquals(entity.participantsId, participant.participantsId)
        assertThat(entity.participantsId).isEqualTo(3)
        assertThat(entity.userName).isEqualTo("username")
    }

    @Test
    fun `test find by username`(){
        //given
        every { participantService.findParticipantByUserName("username2")} returns participants
        //when
        val result = testUnderClass.findByUsername("username2")
        val entity: List<Participant> = result.entity as List<Participant>
        //then
        assertEquals(Response.Status.OK.statusCode, result.status)
        assertEquals(entity[1].participantsId, participants[1].participantsId)
        assertThat(entity[1].participantsId).isEqualTo(2)
        assertThat(entity[1].userName).isEqualTo("username2")
    }

    @Test
    fun `test edit participant`(){
        //given
        every { participantService.editParticipantById(3, participant) } returns participant
        //when
        val result: Response = testUnderClass.editParticipant(3, participant)
        //then
        assertThat(result.entity).isEqualTo(participant)
        assertThat(result).isNotNull
        assertEquals(Response.Status.OK.statusCode, result.status)
        assertEquals(result.entity, participant)
    }

    @Test
    @Disabled
    fun `test delete participant`(){

    }


}
