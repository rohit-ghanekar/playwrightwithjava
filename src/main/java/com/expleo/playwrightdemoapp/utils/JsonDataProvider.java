package com.expleo.playwrightdemoapp.utils;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

public class JsonDataProvider {
    static Object obj;
    public JsonDataProvider() {
        File filename = new File(System.getProperty("user.dir")+"/src/test/resources/data.json");
        //convert json file into string
        String json;
        try {
            json = FileUtils.readFileToString(filename, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //parse the string into object
        try {
            obj = new JSONParser().parse(json);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject getJsonData() {

        //give jsonobject o that I can return it to the function everytime it get called
        return (JSONObject) obj;

    }
}
