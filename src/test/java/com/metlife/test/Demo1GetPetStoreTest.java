package com.metlife.test;

import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Demo1GetPetStoreTest {
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
    public void findInvalidPetTest()
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

    /*
    Get Request For query Parameter
     */
    @Test
    public void findPetByValidStatusTest()
    {
        String status="sold";
        String resource="/pet/findByStatus?status="+status;

        String response =RestAssured
                .given()
                .when().get(endPoint+resource)
                .then().statusCode(200).extract().asString();

        System.out.println(response);
    }
    @Test
    public void findPetByValidStatus2Test()
    {
//        String status="sold";
        String resource="/pet/findByStatus";

        String response =RestAssured
                .given().queryParam("status","sold")
                .when().get(endPoint+resource)
                .then().statusCode(200).extract().asString();

        System.out.println(response);
    }
}
