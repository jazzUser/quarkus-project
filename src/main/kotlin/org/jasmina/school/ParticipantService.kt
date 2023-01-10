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

    fun addParticipantToTraining(trainingId: Long, participantId: Long) {

        var participant = participantsRepository.findById(participantId)
        var training = trainingRepository.findById(trainingId)

        if (training != null && participant!= null) {
            var trainings = participant.trainings
            trainings.add(training)
            participant.trainings = trainings
            participantsRepository.persist(participant)
        }
    }
}