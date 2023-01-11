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

    fun getParticipantByUserName(userName:String) : List<Participant> {

        val username =participantsRepository.getParticipantByUserName(userName)

        if(username == emptyList<Participant>()){
            throw  IllegalArgumentException("There is no User with Username $userName")
        }
        return username
    }

    fun editParticipantById(participantId: Long, participantToUpdate: Participant): Participant {
        val oldParticipant: Participant = this.participantsRepository.findById(participantId)
        oldParticipant.participantsId.also { participantToUpdate.participantsId = it }
        return participantToUpdate
    }

}