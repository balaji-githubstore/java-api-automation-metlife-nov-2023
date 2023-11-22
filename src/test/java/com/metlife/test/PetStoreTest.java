package com.metlife.test;

import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PetStoreTest {
    public static String endPoint="https://petstore.swagger.io/v2";

    /*
    Get Request For Path Parameter
     */
    @Test
    public void findValidPetTest()
    {
        int petId=62;
        String resource="/pet/"+petId;

        String response =RestAssured
                .given()
                .when().get(endPoint+resource)
                .then().statusCode(200).extract().asString();

        System.out.println(response);

        Assert.assertTrue(response.contains(":62"));
        Assert.assertTrue(response.contains("Lana"));
    }

    @Test
    public void findInvalidPet()
    {
        int petId=68777;
        String resource="/pet/"+petId;

        String response =RestAssured
                .given()
                .when().get(endPoint+resource)
                .then().statusCode(404).extract().asString();

        System.out.println(response);

        Assert.assertTrue(response.contains("Pet not found"));
    }
}
