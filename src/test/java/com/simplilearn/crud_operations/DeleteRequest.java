package com.simplilearn.crud_operations;

import com.simplilearn.Configuration;
import io.restassured.RestAssured;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class DeleteRequest {

    @Test
    public void deleteCall() {

        String baseUri = Configuration.getProperty("BASEURI");

        String path = Configuration.getProperty("PATH");

        String id = Configuration.getProperty("ID");

        Logger logger = Logger.getLogger("EmployeesLogs");

        logger.setLevel(Level.DEBUG);

        logger.info("============= Start of Delete Call ====================");

        RestAssured.given()
                .baseUri(baseUri)
                .when()
                .delete(path + id)
                .then()
                .statusCode(200)
                .log()
                .all();

        logger.info("===================== End of Delete Call =======================");

    }

}
