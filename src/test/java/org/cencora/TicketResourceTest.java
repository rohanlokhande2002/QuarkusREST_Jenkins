package org.cencora;


import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import org.cencora.model.Ticket;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class TicketResourceTest {

    @Inject
    TicketResource ticketResource;

    @Test
    public void getTickets(){
        Response response = new TicketResource().getTickets();
        List<Ticket> expected = response.readEntity(new GenericType<List<Ticket>>() {});
        List<Ticket> actual = given()
                .when().get("/tickets/get")
                .then()
                .statusCode(200)
                .extract()
                .as(new TypeRef<List<Ticket>>() {
                });
        assertEquals(expected.size(),actual.size());
    }

    @Test
    public void testAddTicket() {
        // Create a new ticket object
        Ticket ticket = new Ticket();

        Random random = new Random();
        ticket.setId(random.nextInt());
        ticket.setDescription("New Ticket for Testing");
        ticket.setDate(LocalDate.parse("2025-12-10"));

        // Call the /tickets/add endpoint
        given()
                .contentType("application/json")
                .body(ticket)
                .when().post("/tickets/add")
                .then()
                .statusCode(200) // should return OK
                .body("description", equalTo("New Ticket for Testing"))
                .body("date", equalTo("2025-12-10"))
                .body("id", notNullValue()); // Panache assigns an ID
    }

    @Test
    public void testGetTicketById() {
        // First add a ticket
        Response response = new TicketResource().getTicketById(100);
        Ticket expected = response.readEntity(new GenericType<Ticket>() {});

        // Now fetch it by id
        Ticket actual = given()
                .when().get("/tickets/get/" + 100)
                .then()
                .statusCode(200)
                .extract()
                .as(new TypeRef<Ticket>() {
                });

        assertEquals(expected.getId(),actual.getId());
        assertEquals(expected.getDescription(),actual.getDescription());
        assertEquals(expected.getDate(),actual.getDate());
    }

}
