package com.group_91.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Iterator;

/**
 * This class defines tools for handling JSON file I/O.
 *
 * @author Yingke Ding
 */
public class Jsons {

    /**
     * Get info from a particular json file.
     * @param name json file's name. Example: name = "customers" for customers.json
     * @return JSONArray
     */
    public static JSONArray getJSONArray(String name) {
        String pathname = "./json_files/" + name + ".json";
        File file = new File(pathname);
        JSONArray array = null;
        InputStreamReader reader;

        if (file.exists()) {
            int ch;
            StringBuilder stringBuilder = new StringBuilder();

            try {
                reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);

                // Read json file. Load object tuples into array.
                while ((ch = reader.read()) != -1) {
                    stringBuilder.append((char) ch);
                }

                // Parse all lines into an JSONArray.
                array = (JSONArray) JSONArray.parse(stringBuilder.toString());

                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return array;
    }


    /**
     * Get a particular json file size (how many Objects in the JSONArray).
     * @param name json file's name.
     * @return the size of a json file.
     */
    public static int getJSONFileSize(String name) {
        return getJSONArray(name).size();
    }


    /**
     * Save a JSONObject into a json file.
     * @param jsonObject the JSONObject to be saved.
     * @param name indicates which json file to be saved.
     * @throws Exception if failed to load json files.
     */
    public static void saveJson(Object jsonObject, String name) throws Exception {
        String pathname = "./json_files/" + name + ".json";
        File file = new File(pathname);
        JSONArray array;
        InputStreamReader reader;
        BufferedWriter writer;

        if (file.exists()) {  // Save into an existing json file.
            int ch = 0;
            StringBuilder stringBuilder = new StringBuilder();
            reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);

            // Read json file. Load object tuples into array.
            while ((ch = reader.read()) != -1) {
                stringBuilder.append((char) ch);
            }

            // Parse all lines into an JSONArray.
            array = (JSONArray) JSONArray.parse(stringBuilder.toString());
            array.add(jsonObject);

            reader.close();

        }
        else {  // Will create a new JSONArray to be saved in json file.
            array = new JSONArray();
            array.add(jsonObject);
        }

        writer = new BufferedWriter(new FileWriter(pathname));
        writer.write(array.toString());
        writer.close();

    }


    /**
     * Save a new JSONArray into a JSON file. Will delete the original contents.
     * @param jsonArray a JSONArray to be saved.
     * @param name json file's name.
     * @throws IOException for not-found file.
     */
    public static void saveJson(JSONArray jsonArray, String name) throws IOException {
        String pathname = "./json_files/" + name + ".json";
        new FileWriter(pathname, false).close();  // delete original content
        BufferedWriter writer = new BufferedWriter(new FileWriter((pathname)));

        writer.write(jsonArray.toString());
        writer.close();
    }


    /**
     * Update one tuple in a json file. Locate the tuple by id.<p></p><p></p>
     *
     * Example: <code>updateJSONObjectInFile(newCustomer, "CUST000001", "customerID", "customers")</code>
     * is used to update the "CUST000001" tuple in customers.json with newCustomer. <p></p>
     *
     * CANNOT MODIFY jsonObject ID!!!!
     *
     * @param jsonObject The JSONObject to be updated.
     * @param originalID Used to find the specific tuple.
     * @param idName The name of id in the json file.
     * @param fileName json file's name.
     * @return true if successfully updated.
     * @throws IOException for not-found file.
     */
    public static boolean update(JSONObject jsonObject, String originalID, String idName, String fileName) throws IOException {
        JSONArray array = getJSONArray(fileName);
        int index = -1;

        for (int i = 0; i < array.size(); ++i) {
            JSONObject currentJSONObject = array.getJSONObject(i);

            if (currentJSONObject.get(idName).equals(originalID)) {
                index = i;
            }
        }

        if (index >= 0) {
            array.remove(index);
            array.add(index, jsonObject);
            saveJson(array, fileName);
            return true;
        }
        else return false;
    }

}
