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

    fun addParticipantToTraining(participantId: Long, trainingId: Long, ) {

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
        var participant = participantsRepository.findById(participantId)
        if (participant != null){
            return participant
        }else{
            throw IllegalArgumentException("There is no participant with the ID $participantId")
        }
    }

}