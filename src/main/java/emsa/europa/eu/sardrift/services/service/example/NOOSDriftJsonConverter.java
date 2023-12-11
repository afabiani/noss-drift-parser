package emsa.europa.eu.sardrift.services.service.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import emsa.europa.eu.sardrift.services.service.example.data.in.SimulationModelCluster;
import emsa.europa.eu.sardrift.services.service.example.data.out.SimulationModelClusterTrajectory;
import emsa.europa.eu.sardrift.services.service.example.data.out.SimulationModelTrajectory;
import emsa.europa.eu.sardrift.services.service.example.io.JsonParser;
import emsa.europa.eu.sardrift.services.service.example.data.in.Simulation;
import emsa.europa.eu.sardrift.services.service.example.io.JsonWriter;

public class NOOSDriftJsonConverter {

    public static void main(String[] args) {
        try {
            // Specify the path to the JSON file inside the "data" folder
            final int simulationID = 1473;
            final String simulationJsonFilePrefix = "noosdrift_" + simulationID;
            final String simulationJsonInputFile = "src/main/resources/data/mme_output/"+simulationJsonFilePrefix+".json";

            // Now you can access the parsed data
            Simulation simulation = JsonParser.readSimulationFromJsonFile(simulationJsonInputFile);
            System.out.println("Type: " + simulation.getType());
            System.out.println("Simulations: " + simulation.getSimulations());
            System.out.println("Coverage Lonmin: " + simulation.getCoverage().getLonmin());
            System.out.println("Coverage Lonmax: " + simulation.getCoverage().getLonmax());

            // Access point-clouds
            List<SimulationModelClusterTrajectory> simulationModelClusters = new ArrayList<SimulationModelClusterTrajectory>();
            for(Entry<String, String> simulationModel : simulation.getSimulations().entrySet())
            {
                // Load the Cluster Point-Clouds
                System.out.println("Loading Cluster Point Cloud: " + simulationModel.getValue());
                final String simulationClusterPointCloudJsonInputFile = "src/main/resources/data/mme_output/"+simulationJsonFilePrefix+"_"+simulationModel.getValue()+".json";
                SimulationModelCluster simulationModelCluster = JsonParser.readSimulationClusterPointCloudFromJsonFile(simulationClusterPointCloudJsonInputFile);
                System.out.println("Model Cluster: " + simulationModelCluster.getProperties().getModelName());
                System.out.println("Model Cluster # Times: " + simulationModelCluster.getProperties().getNumberOfTimes());
                System.out.println("Model Cluster Start Time: " + simulationModelCluster.getProperties().getStartTime());
                System.out.println("Model Cluster End Time: " + simulationModelCluster.getProperties().getEndTime());
                System.out.println("Model Cluster Time Step: " + simulationModelCluster.getProperties().getTimeStep());

                // Dump the Models
                dumpSimulationModel(simulationModel, simulation, simulationModelCluster, simulationJsonFilePrefix);

                // Dump the Cluster Point-Clouds
                dumpSimulationModelPointClouds(simulationModel, simulationModelCluster, simulationJsonFilePrefix);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void dumpSimulationModel(Entry<String, String> simulationModel, Simulation simulation, SimulationModelCluster simulationModelCluster, String simulationJsonFilePrefix) throws IOException {
        // Access features
        SimulationModelTrajectory simulationModelTrajectory = new SimulationModelTrajectory();
        for (Simulation.Feature modelFeature : simulation.getFeatures()) {
            simulationModelTrajectory.addFromModelTrajectoryFeature(simulationModel.getKey(), simulationModel.getValue(), simulation.getCoverage(), modelFeature, simulationModelCluster.getProperties());
        }

        // Call the writeSimulationToJsonFile method from JsonWriter
        final String simulationModelPointCloudJsonOutputFile = "src/main/resources/data/mme_output/geojson/"+ simulationJsonFilePrefix +"_"+ simulationModel.getValue()+".json";
        JsonWriter.writeSimulationModelTrajectoryToJsonFile(simulationModelTrajectory, simulationModelPointCloudJsonOutputFile);
    }

    private static void dumpSimulationModelPointClouds(Entry<String, String> simulationModel, SimulationModelCluster simulationModelCluster, String simulationJsonFilePrefix) throws IOException {
        // Access features
        SimulationModelClusterTrajectory modelClusterTrajectory = new SimulationModelClusterTrajectory();
        for(SimulationModelCluster.Feature modelClusterFeature: simulationModelCluster.getFeatures()){
            modelClusterTrajectory.addFromModelClusterTrajectoryFeature(simulationModel.getKey(), simulationModel.getValue(), modelClusterFeature, simulationModelCluster.getProperties());
        }

        // Call the writeSimulationToJsonFile method from JsonWriter
        final String simulationClusterPointCloudJsonOutputFile = "src/main/resources/data/mme_output/geojson/"+ simulationJsonFilePrefix +"_"+ simulationModel.getValue()+"_cluster.json";
        JsonWriter.writeSimulationModelClusterTrajectoryToJsonFile(modelClusterTrajectory, simulationClusterPointCloudJsonOutputFile);
    }
}