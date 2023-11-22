package com.metlife.test;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Demo4JsonRead {
    public static void main(String[] args) throws IOException {

        FileInputStream file=new FileInputStream("test-data/add_pet.json");

        ObjectMapper mapper=new ObjectMapper();
        JsonNode json=mapper.readTree(file);

        System.out.println(json.get("id").asInt());
        System.out.println(json.get("name"));
        System.out.println(json.get("category"));

        System.out.println(json.get("category").get("id"));
        System.out.println(json.get("category").get("name"));
        System.out.println(json.get("tags"));
        System.out.println(json.get("tags").get(0));

        System.out.println(json.get("tags").get(0).get("id"));
        System.out.println(json.get("photoUrls"));
        System.out.println(json.get("photoUrls").get(1));
    }
}
