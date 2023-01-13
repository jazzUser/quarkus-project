package org.jasmina.school
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@QuarkusTest
class ParticipantResourceTest {

     var participant = Participant(1,"user", "test", "last", mutableListOf())

    @Test
    fun getAllParticipants() {
        RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(participant)
            .get("/participants")
            .then()
            .statusCode(Response.Status.CREATED.statusCode)
            .body(Matchers.notNullValue())
    }

    @Test
    fun addParticipant() {
        RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(participant)
            .post("/participants/add")
            .then()
            .statusCode(Response.Status.CREATED.statusCode)
            .body(Matchers.notNullValue())
    }

    @Test
    fun enrollParticipantToTraining() {
    }

    @Test
    fun findParticipantById() {
    }

    @Test
    fun findByCategory() {
    }

    @Test
    fun updateParticipant() {
    }

    @Test
    fun getParticipantService() {
    }

    @Test
    fun setParticipantService() {
    }
}