package jsonprocessing;

import com.google.gson.*;

import java.io.FileReader;
import java.io.IOException;

/** The example demonstrates reading from a JSON file */
public class JsonProcessor {
    public static void main(String[] args) {
        JsonProcessor jp = new JsonProcessor();
        Fruit fruitObj;
        fruitObj = jp.parseFruitFile("src/main/resources/fruit.json");
        // More general:
        // fruitObj  = (Fruit)jp.parseJson("src/main/resources/fruit.json", Fruit.class);
        System.out.println(fruitObj);

        //Person person = (Person)jp.parseJson("src/main/resources/personInfo.json", Person.class);
        //System.out.println(person);
        Person[] people;
        // people = jp.parsePeopleArray("src/main/resources/people.json");

        // More general
        //people = (Person[])jp.parseJsonWithArrayValue("src/main/resources/people.json", "people", Person[].class);
        /*
        for (Person p: people)
            System.out.println(p);
         */
    }

    /**
     * This method demonstrates how to parse a simple json file
     * that contains info about fruits; using GSON library.
     * Look at fruit.json and class Fruit.
     *
     * @param filePath path to the json file
     * @return Fruit
     */
    public Fruit parseFruitFile(String filePath) {
        Gson gson = new Gson();
        System.out.println("Created the following object from fruit.json :");
        System.out.println();

        try (FileReader fr = new FileReader(filePath)) {
            Fruit fruit = gson.fromJson(fr, Fruit.class);
            return fruit;
        } catch (IOException e) {
            System.out.println("Could not read the file:" + e);
        }
        return null;
    }


    /**
     * A more general method that can be used to convert a json file to the object of a given class/type.
     * @param filename filename
     * @param objectType class of an object to read from file
     * @return Object it created from json
     */
    public Object parseJson(String filename, Class objectType) {
        Gson gson = new Gson();
        try (FileReader fr = new FileReader(filename)) {
            Object p = gson.fromJson(fr, objectType);
            System.out.println("Created the following object from json file:");
            System.out.println();
            return p;
        } catch (IOException e) {
            System.out.println("Could not read the file: " + e);
        }
        return null;
    }

    /**
     * This method demonstrates how to parse a json file
     * that contains info about several people (value stored in a JSON Array).
     * Uses GSON library.
     *
     * @param filePath path to the json file
     * @return array of Person objects
     */
    public Person[] parsePeopleArray(String filePath) {
        Gson gson = new Gson();
        try (FileReader fr = new FileReader(filePath)) {
            JsonObject jo = (JsonObject) JsonParser.parseReader(fr);
            JsonArray jsonArr = jo.getAsJsonArray("people");

            // The commented code is if we want to use an ArrayList instead of array of Person-s
            //Type peopleType = new TypeToken<ArrayList<Person>>(){}.getType();
            //ArrayList<Person> people = gson.fromJson(jsonArr, peopleType);
            Person[] people = gson.fromJson(jsonArr, Person[].class);
            return people;

        } catch (IOException e) {
            System.out.println("Could not read the file: " + e);
        }
        return null;
    }

    /**
     * A more general method to parse a json file where the value of a given key is a JsonArray
     * @param filePath filepath where json file is stored
     * @param key key
     * @param objectType Type of objects in the JsonArray
     * @return
     */
    public Object parseJsonWithArrayValue(String filePath, String key, Class objectType) {
        Gson gson = new Gson();
        try (FileReader fr = new FileReader(filePath)) {
            JsonObject jo = (JsonObject) JsonParser.parseReader(fr);
            JsonArray jsonArr = jo.getAsJsonArray(key);

            // The commented code is if we want to use an ArrayList instead of array of Person-s
            //Type peopleType = new TypeToken<ArrayList<Person>>(){}.getType();
            //ArrayList<Person> people = gson.fromJson(jsonArr, peopleType);
            Object jsonArrayValue = gson.fromJson(jsonArr, objectType);
            return jsonArrayValue;

        } catch (IOException e) {
            System.out.println("Could not read the file: " + e);
        }
        return null;
    }

}
