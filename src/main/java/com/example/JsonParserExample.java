package com.example;

import com.example.data.Feature;
import com.example.data.Simulation;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.List;

public class JsonParserExample {

    public static void main(String[] args) {
        try {
            // Specify the path to the JSON file inside the "data" folder
            String jsonFilePath = "src/main/resources/data/mme_output/noosdrift_1487.json";

            // Create an ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            // Read the JSON file and parse it into the Simulation class
            Simulation simulation = objectMapper.readValue(Paths.get(jsonFilePath).toFile(), Simulation.class);

            // Now you can access the parsed data
            System.out.println("Type: " + simulation.getType());
            System.out.println("Simulations: " + simulation.getSimulations());
            System.out.println("Coverage Lonmin: " + simulation.getCoverage().getLonmin());
            System.out.println("Coverage Lonmax: " + simulation.getCoverage().getLonmax());

            // Access features
            for (Feature feature : simulation.getFeatures()) {
                System.out.println("Feature Time: " + feature.getTime());
                System.out.println("Feature SuperEllipse Centerlon: " + feature.getSuperEllipse().getCenterlon());
                // Access ellipses and clusters as needed
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
