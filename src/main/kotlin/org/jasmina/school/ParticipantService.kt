package org.jasmina.school

import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ParticipantService(private var participantsRepository: ParticipantsRepository, private var trainingRepository: TrainingRepository) {

    fun getAllParticipants(): List<Participant> {
        return participantsRepository.listAll()
    }

    fun addParticipant(participant: Participant): Participant {
        participantsRepository.persist(participant)
        return participant
    }

    fun enrollParticipantToTraining(participantId: Long, trainingId: Long) {

        val participant = participantsRepository.findById(participantId)
        val training = trainingRepository.findById(trainingId)

        if (training != null && participant!= null) {
            val trainings = participant.trainings
            trainings.add(training)
            participant.trainings = trainings
            participantsRepository.persist(participant)
        }
    }

   fun findParticipantById(participantId: Long): Participant {
        val participant = participantsRepository.findById(participantId)
        if (participant != null){
            return participant
        }else{
            throw IllegalArgumentException("There is no participant with the ID $participantId")
        }
   }

    fun getParticipantByUserName(userName:String): List<Participant> {
        val username =participantsRepository.getParticipantByUserName(userName)
        if(username == emptyList<Participant>()){
            throw  IllegalArgumentException("There is no User with Username $userName")
        }
        return username
    }

   fun editParticipantById(participantsId: Long, participantToUpdate: Participant) {
       val participant = participantsRepository.findById(participantsId)
       participant.participantsId = participantToUpdate.participantsId
       participant.userName = participantToUpdate.userName
       participant.firstName = participantToUpdate.firstName
       participant.lastName = participantToUpdate.lastName
       participant.trainings = participantToUpdate.trainings
       participantsRepository.persist(participant)
   }
}

