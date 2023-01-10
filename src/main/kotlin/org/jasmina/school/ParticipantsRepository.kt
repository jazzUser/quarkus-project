package org.jasmina.school

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ParticipantsRepository: PanacheRepositoryBase<Participant, Long> {
}