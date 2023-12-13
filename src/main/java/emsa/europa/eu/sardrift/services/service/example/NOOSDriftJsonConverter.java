package emsa.europa.eu.sardrift.services.service.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Logger;

import emsa.europa.eu.sardrift.services.service.example.data.in.SimulationModelCluster;
import emsa.europa.eu.sardrift.services.service.example.data.out.SimulationModelClusterTrajectory;
import emsa.europa.eu.sardrift.services.service.example.data.out.SimulationModelTrajectory;
import emsa.europa.eu.sardrift.services.service.example.io.JsonParser;
import emsa.europa.eu.sardrift.services.service.example.data.in.Simulation;
import emsa.europa.eu.sardrift.services.service.example.io.JsonWriter;

public class NOOSDriftJsonConverter {

    private static final Logger LOGGER = Logger.getLogger(NOOSDriftJsonConverter.class.getName());

    public static void main(String[] args) {
        try {
            // Specify the path to the JSON file inside the "data" folder
            final int simulationID = 1508;
            final String simulationJsonFilePrefix = "noosdrift_" + simulationID;
            final String simulationJsonInputFile = "src/main/resources/data/mme_output/"+simulationJsonFilePrefix+".json";

            // Now you can access the parsed data
            Simulation simulation = JsonParser.readSimulationFromJsonFile(simulationJsonInputFile);
            LOGGER.fine("Type: " + simulation.getType());
            LOGGER.fine("Simulations: " + simulation.getSimulations());
            LOGGER.fine("Coverage Lonmin: " + simulation.getCoverage().getLonmin());
            LOGGER.fine("Coverage Lonmax: " + simulation.getCoverage().getLonmax());

            // Access point-clouds
            List<SimulationModelClusterTrajectory> simulationModelClusters = new ArrayList<SimulationModelClusterTrajectory>();
            for(Entry<String, String> simulationModel : simulation.getSimulations().entrySet())
            {
                // Load the Cluster Point-Clouds
                LOGGER.fine("Loading Cluster Point Cloud: " + simulationModel.getValue());
                final String simulationClusterPointCloudJsonInputFile = "src/main/resources/data/mme_output/"+simulationJsonFilePrefix+"_"+simulationModel.getValue()+".json";
                SimulationModelCluster simulationModelCluster = JsonParser.readSimulationClusterPointCloudFromJsonFile(simulationClusterPointCloudJsonInputFile);
                LOGGER.fine("Model Cluster: " + simulationModelCluster.getProperties().getModelName());
                LOGGER.fine("Model Cluster # Times: " + simulationModelCluster.getProperties().getNumberOfTimes());
                LOGGER.fine("Model Cluster Start Time: " + simulationModelCluster.getProperties().getStartTime());
                LOGGER.fine("Model Cluster End Time: " + simulationModelCluster.getProperties().getEndTime());
                LOGGER.fine("Model Cluster Time Step: " + simulationModelCluster.getProperties().getTimeStep());

                // Dump the Models
                dumpSimulationModel(simulationModel, simulation, simulationModelCluster, simulationJsonFilePrefix);

                // Dump the Cluster Point-Clouds
                dumpSimulationModelPointClouds(simulationModel, simulationModelCluster, simulationJsonFilePrefix);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void dumpSimulationModel(Entry<String, String> simulationModel,
                                            Simulation simulation,
                                            SimulationModelCluster simulationModelCluster,
                                            String simulationJsonFilePrefix) throws IOException, InterruptedException {
        // Access features
        SimulationModelTrajectory simulationModelTrajectory = new SimulationModelTrajectory();
        for (Simulation.Feature modelFeature : simulation.getFeatures()) {
            simulationModelTrajectory.addFromModelTrajectoryFeature(simulationModel.getKey(), simulationModel.getValue(), simulation.getCoverage(), modelFeature, simulationModelCluster.getProperties());
        }

        // Call the writeSimulationToJsonFile method from JsonWriter
        final String simulationModelPointCloudJsonOutputFile = "src/main/resources/data/mme_output/geojson/"+ simulationJsonFilePrefix +"_"+ simulationModel.getValue()+".json";
        JsonWriter.writeSimulationModelToJsonFile(simulationModelTrajectory, simulationModelPointCloudJsonOutputFile);
        JsonWriter.publishSimulationModelToJsonFileJDBC("NOOS", "postgres", "postgres", "localhost", 5432, "noosdrift_model", simulationModelTrajectory);
    }

    private static void dumpSimulationModelPointClouds(Entry<String, String> simulationModel,
                                                       SimulationModelCluster simulationModelCluster,
                                                       String simulationJsonFilePrefix) throws IOException, InterruptedException {
        // Access features
        SimulationModelClusterTrajectory modelClusterTrajectory = new SimulationModelClusterTrajectory();
        for(SimulationModelCluster.Feature modelClusterFeature: simulationModelCluster.getFeatures()){
            modelClusterTrajectory.addFromModelClusterTrajectoryFeature(simulationModel.getKey(), simulationModel.getValue(), modelClusterFeature, simulationModelCluster.getProperties());
        }

        // Call the writeSimulationToJsonFile method from JsonWriter
        final String simulationClusterPointCloudJsonOutputFile = "src/main/resources/data/mme_output/geojson/"+ simulationJsonFilePrefix +"_"+ simulationModel.getValue()+"_cluster.json";
        JsonWriter.writeSimulationModelToJsonFile(modelClusterTrajectory, simulationClusterPointCloudJsonOutputFile);
        JsonWriter.publishSimulationModelToJsonFileJDBC("NOOS", "postgres", "postgres", "localhost", 5432, "noosdrift_cluster", modelClusterTrajectory);
    }
}