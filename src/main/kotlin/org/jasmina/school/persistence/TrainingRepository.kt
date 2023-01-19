package org.jasmina.school.persistence

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase
import org.jasmina.school.model.Training
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class TrainingRepository: PanacheRepositoryBase<Training, Long> {
}