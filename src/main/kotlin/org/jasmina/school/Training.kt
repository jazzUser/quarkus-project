package org.jasmina.school
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class Training(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name= "training_id", nullable = false)
    var trainingId :Long = 1,

    @Column(name = "title", nullable = false)
    var title: String ="",

    @Column(name = "category", nullable = false)
    var category: String ="",

    @ManyToMany(mappedBy = "trainings")
    @JsonIgnore
    var participants: MutableList<Participant> = mutableListOf()
)
