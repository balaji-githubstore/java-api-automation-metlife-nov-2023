package com.metlife.test;

import io.restassured.path.json.JsonPath;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Objects;

public class Demo3JsonRead {
    public static void main(String[] args) throws FileNotFoundException {

        FileInputStream file=new FileInputStream("test-data/update_pet.json");
        JsonPath json=new JsonPath(file);

        int id=json.get("id");
        System.out.println(id);

        String name=json.get("name");
        System.out.println(name);

        HashMap<String, Objects> cat=json.get("category");
        System.out.println(cat);
        System.out.println(cat.get("id"));
    }
}
