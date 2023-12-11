package emsa.europa.eu.sardrift.services.service.example.io;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import emsa.europa.eu.sardrift.services.service.example.data.in.Simulation;
import com.fasterxml.jackson.databind.ObjectMapper;
import emsa.europa.eu.sardrift.services.service.example.data.out.SimulationModelClusterTrajectory;
import emsa.europa.eu.sardrift.services.service.example.data.out.SimulationModelTrajectory;

import java.nio.file.Paths;
import java.io.IOException;

public class JsonWriter {

    // Create an ObjectMapper instance
    static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());
    }

    public static void writeSimulationToJsonFile(Simulation simulation, String jsonFilePath) throws IOException {
        // Convert the Simulation object to a JSON string
        // String jsonString = objectMapper.writeValueAsString(simulation);

        // Write the JSON string to a file
        objectMapper.writeValue(Paths.get(jsonFilePath).toFile(), simulation);

        System.out.println("JSON written to: " + jsonFilePath);
    }

    public static void writeSimulationModelTrajectoryToJsonFile(SimulationModelTrajectory simulationModelTrajectory, String jsonFilePath) throws IOException {
        // Write the JSON string to a file
        objectMapper.writeValue(Paths.get(jsonFilePath).toFile(), simulationModelTrajectory);

        System.out.println("JSON written to: " + jsonFilePath);
    }

    public static void writeSimulationModelClusterTrajectoryToJsonFile(SimulationModelClusterTrajectory modelClusterTrajectory, String jsonFilePath) throws IOException {
        // Write the JSON string to a file
        objectMapper.writeValue(Paths.get(jsonFilePath).toFile(), modelClusterTrajectory);

        System.out.println("JSON written to: " + jsonFilePath);
    }
}
