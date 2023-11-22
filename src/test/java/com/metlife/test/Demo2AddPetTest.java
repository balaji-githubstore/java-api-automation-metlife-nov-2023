package com.metlife.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Demo2AddPetTest {
    public static String endPoint="https://petstore.swagger.io/v2";

    /*
    Add pet to store - using harded json as a string
     */
    @Test
    public void addValidPet1Test()
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

    @Test
    public void addValidPet2Test() throws FileNotFoundException {
        FileInputStream file=new FileInputStream("test-data/add_pet.json");
        JsonPath json=new JsonPath(file);
        String jsonBody= json.prettify();
        System.out.println(jsonBody);

        String resource="/pet";

        String response=  RestAssured
                .given().header("Content-Type","application/json").body(jsonBody)
                .when().post(endPoint+resource)
                .then().log().all().statusCode(200).extract().asString();

        Assert.assertTrue(response.contains("692"));

    }

    /*
    Update pet details
     */
    @Test
    public void updateValidPet2Test() throws FileNotFoundException {
        FileInputStream file=new FileInputStream("test-data/update_pet.json");
        JsonPath json=new JsonPath(file);
        String jsonBody= json.prettify();
        System.out.println(jsonBody);

        String resource="/pet";
        String response=  RestAssured
                .given().header("Content-Type","application/json").body(jsonBody)
                .when().put(endPoint+resource)
                .then().log().all().statusCode(200).extract().asString();

        Assert.assertTrue(response.contains("692"));

    }

    @Test
    public void deleteValidPetTest() throws JsonProcessingException {
        int petId=692;
        String resource="/pet/"+petId;

        //write code to delete pet
        //get the response as string and print it
        String response=  RestAssured
                .given().header("api_key","isdsdji8888u8u8us8d")
                .when().delete(endPoint+resource)
                .then().statusCode(200).extract().asString();
        System.out.println(response);

        ObjectMapper mapper=new ObjectMapper();
        JsonNode actualJson=mapper.readTree(response);

        System.out.println(actualJson.get("message"));
        ;
        Assert.assertEquals(actualJson.get("message").asInt(),692);
    }

    @Test
    public void deleteInValidPetTest() throws JsonProcessingException {
        int petId=692;
        String resource="/pet/"+petId;

        //write code to delete pet
        //get the response as string and print it
        String response=  RestAssured
                .given().header("api_key","isdsdji8888u8u8us8d")
                .when().delete(endPoint+resource)
                .then().statusCode(404).extract().asString();
        System.out.println(response);


    }
}
