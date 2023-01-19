package org.jasmina.school.unittests.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.jasmina.school.*
import org.jasmina.school.model.Participant
import org.jasmina.school.model.Training
import org.jasmina.school.persistence.ParticipantRepository
import org.jasmina.school.persistence.TrainingRepository
import org.jasmina.school.service.ParticipantService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import javax.ws.rs.core.Response

@ExtendWith(MockKExtension::class)
class ParticipantServiceUnitTest {

    @MockK
    private lateinit var participantRepository: ParticipantRepository

    @MockK
    private lateinit var trainingRepository: TrainingRepository

    @InjectMockKs
    private lateinit var testUnderClass: ParticipantService

    private val participant: Participant = Participant(3, "username", "firstname", "lastname", mutableListOf())

    private val participants: List<Participant> = listOf(
        Participant(1, "username1", "firstname1", "lastname1", mutableListOf()),
        Participant(2, "username2", "firstname2", "lastname3", mutableListOf())
    )

    private val training: Training = Training(2, "Unit Testing", "Test development", mutableListOf())
    @AfterEach
    fun cleanup() {
        unmockkAll()
    }

    @Test
    fun `test get all participants`(){
        //given
        every { participantRepository.listAll() } returns participants
        //when
        val result = testUnderClass.getAllParticipants()
        //then
        assertThat(result).isEqualTo(participants)
        assertFalse(participants.isEmpty())
        assertEquals(participants[1].participantsId, 2)

    }

    @Test
    fun `test add participant`(){
        //given
        every { participantRepository.persist(participant)} returns mockk(relaxed = true)
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
        every { participantRepository.findById(3)} returns participant
        every { trainingRepository.findById(2)} returns training
        every { participantRepository.persist(participant)} returns mockk(relaxed = true)
        //when
        val result = testUnderClass.enrollParticipantToTraining(3,2)
        //then
        assertEquals(participant.trainings[0], training)
        assertThat(result).isNotNull
    }

    @Test
    fun `test find by participant by id`(){
        //given
        every { participantRepository.findById(3) } returns participant
        //when
        val result = testUnderClass.findParticipantById(3)
        //then
        verify(exactly = 1) { participantRepository.findById(any()) }
        //then
        assertThat(result.participantsId).isEqualTo(3)
        assertNotNull(participant)

    }

    @Test
    fun `test find by username`(){
        //given
        every { participantRepository.getParticipantByUserName("username1") } returns participants
        //when
        val result = testUnderClass.findParticipantByUserName("username1")
        //then
        assertThat(result).isNotEmpty
        verify(exactly = 1) { participantRepository.getParticipantByUserName(any()) }
    }

    @Test
    fun `test edit participant`(){
        //given
        every { participantRepository.findById(3) } returns participant
        every {participantRepository.persist(participant)} returns mockk(relaxed = true)
        //when
        participant.userName = "newUsername"
        val result = testUnderClass.editParticipantById(3, participant)
        //then
        assertThat(result.userName).isEqualTo("newUsername")
        assertThat(participant.userName).isEqualTo("newUsername")
        assertThat(result).isNotNull
    }

}