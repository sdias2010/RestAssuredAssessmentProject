package com.simplilearn.crud_operations;

import com.simplilearn.Configuration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class PutRequest {

    @Test
    public void putCall() {

        String baseUri = Configuration.getProperty("BASEURI");

        String path = Configuration.getProperty("PATH");

        String id = Configuration.getProperty("ID");

        Logger logger = Logger.getLogger("EmployeesLogs");

        logger.setLevel(Level.DEBUG);

        logger.info("============= Start of Put Call ====================");

        Map<String, Object> jsonMap = new HashMap<String, Object>();

        jsonMap.put("firstName", "Peter");
        jsonMap.put("lastName", "Paul");
        jsonMap.put("salary", 6000);
        jsonMap.put("email", "Paul@abc.com");

        RestAssured.given()
                .baseUri(baseUri)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(jsonMap)
                .when()
                .put(path + id)
                .then()
                .statusCode(200)
                .body("firstName", Matchers.equalTo("Peter"))
                .body("lastName", Matchers.equalTo("Paul"))
                .body("salary", Matchers.equalTo(6000))
                .body("email", Matchers.equalTo("Paul@abc.com"))
                .log()
                .body();

        logger.info("===================== End of Put Call =======================");
    }
}
