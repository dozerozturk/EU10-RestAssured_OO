package com.cydeo.day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {

    String url = "http://54.172.237.131:8000/api/spartans";

    @Test
    public void test1(){
        //send a get request and save response inside the Response object
        Response response = RestAssured.get(url);//alt+enter for response

        //print response status code
        System.out.println(response.statusCode());

        //print response body
        response.prettyPrint();

    }

}
