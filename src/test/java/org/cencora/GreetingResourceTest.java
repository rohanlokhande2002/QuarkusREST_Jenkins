package org.cencora;

import io.netty.handler.codec.DateFormatter;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static io.smallrye.common.constraint.Assert.assertNotNull;
import static io.smallrye.common.constraint.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@QuarkusTest
class GreetingResourceTest {

    @Inject
    GreetingResource greetingResource;

    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello"));
    }

    @Test
    void testHelloQuarkusEndpoint() {
        given()
                .when().get("/hello/quarkus")
                .then()
                .statusCode(200)
                .body(is("Hello Quarkus!"));
    }

    @Test
    void testQuarkusEndpoint() {
        assertEquals(greetingResource.helloQuarkus(), "Hello Quarkus!");
        assertEquals(greetingResource.helloQuarkus().split(" ").length, 2);
        String msg = greetingResource.helloQuarkus();
        assertTrue(msg.matches("[A-Z a-z!]+"));
    }

    @Test
    void testSayHello() {
        assertEquals(greetingResource.sayHello(), "hello");
        assertNotEquals(greetingResource.sayHello(), "Hello");
        assertNotNull(greetingResource.sayHello());
    }

    @Test
    void testDate(){
        given()
                .when().get("/date")
                .then()
                .statusCode(200)
                .body(is(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
    }
}