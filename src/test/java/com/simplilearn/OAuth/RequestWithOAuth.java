package com.simplilearn.OAuth;

import com.simplilearn.Configuration;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class RequestWithOAuth {

    @Test
    public void getTokenValue() {

        String baseUri = Configuration.getProperty("BASEURI");

        String path = Configuration.getProperty("PATH");

        Logger logger = Logger.getLogger("EmployessLogs");

        logger.setLevel(Level.ERROR);

        logger.info("========= Start of Get Token Value ========");

        Response tokenResponse = RestAssured.given()
                .baseUri(baseUri)
                .auth().preemptive().basic("rest-assured", "password")
                .contentType("application/x-www-form-urlencoded")
                .formParam("grant_type", "password")
                .formParam("username", "onlyfullstack")
                .formParam("password", "secret")
                .when()
                .post("/oauth/token");


        System.out.println(tokenResponse.body().asString());

        JsonPath jpath = tokenResponse.jsonPath();
        String tokenValue  = jpath.get("access_token");
        System.out.println(tokenValue);


        Response getResponse = RestAssured.given()
                .auth()
                .oauth2(tokenValue)
                .baseUri(baseUri)
                .when()
                .get(path);

        System.out.println(getResponse.asString());

        logger.info("========= End of Get Token Value ========");
    }
}
