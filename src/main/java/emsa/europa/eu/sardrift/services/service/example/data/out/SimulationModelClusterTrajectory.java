package emsa.europa.eu.sardrift.services.service.example.data.out;

import emsa.europa.eu.sardrift.services.service.example.data.spatial.FeatureCollection;
import emsa.europa.eu.sardrift.services.service.example.data.in.SimulationModelCluster;

public class SimulationModelClusterTrajectory extends FeatureCollection {

    public void addFromModelClusterTrajectoryFeature(String simulationModelInternalRefId, String simulationModelInternalRefName, SimulationModelCluster.Feature modelClusterFeature, SimulationModelCluster.Properties modelClusterProperties) {
        Feature modelClusterTrajectoryFeature = new Feature();

        Properties properties = new Properties();
        modelClusterTrajectoryFeature.setProperties(properties);

        // Write all the Feature Properties from the original model
        properties.setRequestID(modelClusterProperties.getRequestID());
        properties.setSimulation(simulationModelInternalRefName);
        properties.setModelName(modelClusterProperties.getModelName());
        properties.setTimeStep(modelClusterProperties.getTimeStep());
        properties.setEndTime(modelClusterProperties.getEndTime());
        properties.setStartTime(modelClusterProperties.getStartTime());
        properties.setNumberOfTimes(modelClusterProperties.getNumberOfTimes());
        properties.setOceanForcing(modelClusterProperties.getOceanForcing());
        properties.setWindForcing(modelClusterProperties.getWindForcing());

        properties.setTime(modelClusterFeature.getTime());
        properties.setLatitudeOfCenter(modelClusterFeature.getLatitudeOfCenter());
        properties.setLongitudeOfCenter(modelClusterFeature.getLongitudeOfCenter());
        properties.setEllipsisMinorAxis(modelClusterFeature.getEllipsisMinorAxis());
        properties.setEllipsisMajorAxis(modelClusterFeature.getEllipsisMajorAxis());
        properties.setEllipsisMajorAxisAzimuthAngle(modelClusterFeature.getEllipsisMajorAxisAzimuthAngle());
        properties.setDistanceOfCenterFromStart(modelClusterFeature.getDistanceOfCenterFromStart());
        properties.setAzimuthDirectionOfCenterFromStart(modelClusterFeature.getAzimuthDirectionOfCenterFromStart());

        // Add the Geometry Coordinates to the Feature
        modelClusterTrajectoryFeature.setGeometry(modelClusterFeature.getGeometry());

        // Return the Feature
        this.getFeatures().add(modelClusterTrajectoryFeature);
    }
}
