package org.jasmina.school.service

import org.jasmina.school.model.Training
import org.jasmina.school.persistence.TrainingRepository
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