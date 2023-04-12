package com.cydeo.day4;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CBTrainingApiWithJsonPath {

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://api.training.cydeo.com";

    }

    @DisplayName("GET Request to individual student")
    @Test
    public void test1(){  // HOMEWORK
        //send a get request to student id 23401 as a path parameter and accept header application/json
        //verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = gzip
        //verify Date header exists
        //assert that
            /*
                firstName Vera
                batch 14
                section 12
                emailAddress aaa@gmail.com
                companyName Cybertek
                state IL
                zipCode 60606

                using JsonPath
             */

//ANSWER

        Response response = given().accept(ContentType.JSON).get("/student/all");

        JsonPath jsonPath = response.jsonPath();

        assertEquals(200, response.statusCode());
        assertEquals("application/json;charset=UTF-8", response.header("content-Type"));
        assertTrue(response.headers().hasHeaderWithName("Date"));

        String firstName = jsonPath.getString("students[15].firstName");
        String batch = jsonPath.getString("students[15].batch");
        String section = jsonPath.getString("students[15].section");
        String email = jsonPath.getString("students[15].contact.emailAddress");
        String companyName = jsonPath.getString("students[15].company.companyName");
        String state = jsonPath.getString("students[15].company.address.state");
        String zip = jsonPath.getString("students[15].company.address.zipCode");

        assertEquals("Augusta", firstName);
        assertEquals("15", batch);
        assertEquals("N/A", section);
        assertEquals("cbeech1@sakura.ne.jp", email);
        assertEquals("Wordpedia", companyName);
        assertEquals("North Carolina", state);
        assertEquals("31660", zip);

    }
}