package com.simplilearn.crud_operations;

import com.simplilearn.Configuration;
import io.restassured.RestAssured;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class GetRequest {

    @Test
    public void getCall() {

        String baseUri = Configuration.getProperty("BASEURI");

        String path = Configuration.getProperty("PATH");

        Logger logger = Logger.getLogger("EmployeesLogs");

        logger.setLevel(Level.DEBUG);

        logger.info("============= Start of Get Call ==============");

        RestAssured.given()
                .baseUri(baseUri)
                .queryParam("id",7)
                .when()
                .get(path)
                .then()
                .body("[3].id", Matchers.equalTo(5))
                .body("[3].firstName",Matchers.equalTo("priya"))
                .body("[3].lastName",Matchers.equalTo("shankar"))
                .body("[3].salary",Matchers.equalTo(10000))
                .body("[3].email",Matchers.equalTo("priya@abc.com"))
                .statusCode(200)
                .log()
                .body();

        logger.info("============= End of Get Call =================");

    }
}
