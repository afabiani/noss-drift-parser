package emsa.europa.eu.sardrift.services.service.example.io;

import emsa.europa.eu.sardrift.services.service.example.data.in.Simulation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import emsa.europa.eu.sardrift.services.service.example.data.in.SimulationModelCluster;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class JsonParser {

    private static final Logger LOGGER = Logger.getLogger(JsonParser.class.getName());

    // Create an ObjectMapper instance
    static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());
    }

    public static Simulation readSimulationFromJsonFile(String jsonFilePath) throws IOException {
        // Read the JSON file and parse it into the Simulation class
        return objectMapper.readValue(Paths.get(jsonFilePath).toFile(), Simulation.class);
    }

    public static SimulationModelCluster readSimulationClusterPointCloudFromJsonFile(String jsonFilePath) throws IOException {
        // Read the JSON file and parse it into the Simulation class
        return objectMapper.readValue(Paths.get(jsonFilePath).toFile(), SimulationModelCluster.class);
    }
}
