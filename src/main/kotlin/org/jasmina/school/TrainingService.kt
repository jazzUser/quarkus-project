package org.jasmina.school

import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class TrainingService(private var trainingRepository: TrainingRepository) {
    fun getAllTrainings(): List<Training> {
        return trainingRepository.listAll()
    }
    fun addTraining(training: Training): Training {
        trainingRepository.persist(training)
        return training
    }
}