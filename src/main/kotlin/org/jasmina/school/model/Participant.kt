package org.jasmina.school.model
import javax.persistence.*

@Entity
data class Participant(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "participant_id", nullable = false)
    var participantsId: Long = 1,

    @Column(name = "userName", nullable = false)
    var userName: String="",

    @Column(name =  "firstName", nullable = false)
    var firstName: String="",

    @Column(name =  "lastName", nullable =  false)
    var lastName: String="",

    @ManyToMany
    @JoinTable(
        name = "participants_trainings",
        joinColumns = [JoinColumn(name = "participant_id")],
        inverseJoinColumns = [JoinColumn(name="training_id")],)
    var trainings: MutableList<Training> = mutableListOf()

)
