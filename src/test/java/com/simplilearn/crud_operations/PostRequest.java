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

public class PostRequest {

    @Test
    public void postCall() {

        String baseUri = Configuration.getProperty("BASEURI");

        String path = Configuration.getProperty("PATH");

        Logger logger = Logger.getLogger("EmployeesLogs");

        logger.setLevel(Level.DEBUG);

        logger.info("=================== Start of Post Call ====================");

        Map<String, Object> jsonMap = new HashMap<String, Object>();

        jsonMap.put("firstName", "joy");
        jsonMap.put("lastName", "james");
        jsonMap.put("email", "joy@abc.com");
        jsonMap.put("salary", 4000);


        RestAssured.given()
                .baseUri(baseUri)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(jsonMap)
                .when()
                .post(path)
                .then()
                .statusCode(201)
                .body("firstName", Matchers.equalTo("joy"))
                .body("lastName", Matchers.equalTo("james"))
                .body("email", Matchers.equalTo("joy@abc.com"))
                .body("salary", Matchers.equalTo(4000))
                .log()
                .body();

        logger.info("=================== End of Post Call ===================");

    }
}
