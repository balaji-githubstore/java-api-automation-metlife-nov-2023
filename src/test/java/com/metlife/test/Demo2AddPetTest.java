package com.metlife.test;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class Demo2AddPetTest {
    public static String endPoint="https://petstore.swagger.io/v2";

    @Test
    public void addValidPetTest()
    {
        String resource="/pet";

        String jsonBody="{\n" +
                "  \"id\": 792,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"doggie-792\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";


      String response=  RestAssured
                .given().header("Content-Type","application/json").body(jsonBody)
                .when().post(endPoint+resource)
                .then().log().all().statusCode(200).extract().asString();


    }
}
