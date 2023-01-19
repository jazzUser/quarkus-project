package org.jasmina.school.persistence

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase
import org.jasmina.school.model.Participant
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ParticipantRepository: PanacheRepositoryBase<Participant, Long> {
    fun getParticipantByUserName(userName : String):List<Participant> {
        return list("userName = ?1", userName)
    }
}