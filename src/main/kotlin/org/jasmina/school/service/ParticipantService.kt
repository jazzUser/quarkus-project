package org.jasmina.school.service

import org.jasmina.school.model.Participant
import org.jasmina.school.persistence.ParticipantRepository
import org.jasmina.school.persistence.TrainingRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ParticipantService(private var participantRepository: ParticipantRepository, private var trainingRepository: TrainingRepository) {

    fun getAllParticipants(): List<Participant> {
        return participantRepository.listAll()
    }

    fun addParticipant(participant: Participant): Participant {
        participantRepository.persist(participant)
        return participant
    }

    fun enrollParticipantToTraining(participantId: Long, trainingId: Long): Participant {

        val participant = participantRepository.findById(participantId)
        val training = trainingRepository.findById(trainingId)

        if (training != null && participant!= null) {
            val trainings = participant.trainings
            trainings.add(training)
            participant.trainings = trainings
            participantRepository.persist(participant)
        }
        return participant
    }

   fun findParticipantById(participantId: Long): Participant {
        val participant = participantRepository.findById(participantId)
        if (participant != null){
            return participant
        }else{
            throw IllegalArgumentException("There is no participant with the ID $participantId")
        }
   }

    fun findParticipantByUserName(userName:String): List<Participant> {
        val username =participantRepository.getParticipantByUserName(userName)
        if(username == emptyList<Participant>()){
            throw  IllegalArgumentException("There is no User with Username $userName")
        }
        return username
    }

   fun editParticipantById(participantsId: Long, participantToUpdate: Participant): Participant {
       val participant = participantRepository.findById(participantsId)
       participant.participantsId = participantToUpdate.participantsId
       participant.userName = participantToUpdate.userName
       participant.firstName = participantToUpdate.firstName
       participant.lastName = participantToUpdate.lastName
       participant.trainings = participantToUpdate.trainings
       participantRepository.persist(participant)
       return participant
   }
}

