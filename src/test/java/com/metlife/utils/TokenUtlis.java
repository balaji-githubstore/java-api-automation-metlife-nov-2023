package com.metlife.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;

public class TokenUtlis {
    public static String endPoint="https://petstore.swagger.io/v2";
    public  static  String getAuthToken(int petToken) throws JsonProcessingException {

//        int petId=62;
        String resource="/pet/"+petToken;

        String response = RestAssured
                .given()
                .when().get(endPoint+resource)
                .then().statusCode(200).extract().asString();

        System.out.println(response);

        ObjectMapper mapper=new ObjectMapper();
        JsonNode actualJson=mapper.readTree(response);

        return actualJson.get("id").asText();
    }
}
