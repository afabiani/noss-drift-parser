package emsa.europa.eu.sardrift.services.service.example.data.out;

import emsa.europa.eu.sardrift.services.service.example.data.in.Simulation;
import emsa.europa.eu.sardrift.services.service.example.data.in.SimulationModelCluster;
import emsa.europa.eu.sardrift.services.service.example.data.spatial.FeatureCollection;
import emsa.europa.eu.sardrift.services.service.example.data.spatial.geom.PointGeometry;

public class SimulationModelTrajectory extends FeatureCollection {

    public void addFromModelTrajectoryFeature(String simulationModelInternalRefId, String simulationModelInternalRefName, Simulation.Coverage simulationCoverage, Simulation.Feature modelFeature, SimulationModelCluster.Properties modelClusterProperties) {
        Feature modelTrajectoryFeature = new Feature();

        Properties properties = new Properties();
        modelTrajectoryFeature.setProperties(properties);

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

        properties.setTime(modelFeature.getTime());

        properties.setBboxLonMin(simulationCoverage.getLonmin());
        properties.setBboxLatMin(simulationCoverage.getLatmin());
        properties.setBboxLonMax(simulationCoverage.getLonmax());
        properties.setBboxLatMax(simulationCoverage.getLatmax());
        properties.setBboxCenterLon(simulationCoverage.getCenterlon());
        properties.setBboxCenterLat(simulationCoverage.getCenterlat());

        Simulation.Feature.SuperEllipse superEllipse = modelFeature.getSuperEllipse();
        properties.setSuperEllipsisCenterLon(superEllipse.getCenterlon());
        properties.setSuperEllipsisCenterLat(superEllipse.getCenterlat());
        properties.setSuperEllipsisMinorAxis(superEllipse.getEllipsisMinorAxis());
        properties.setSuperEllipsisMajorAxis(superEllipse.getEllipsisMajorAxis());
        properties.setSuperEllipsisMajorAxisAzimuthAngle(superEllipse.getEllipsisMajorAxisAzimuthAngle());

        Simulation.Feature.Ellipse featureEllipsis = modelFeature.getEllipses().get(simulationModelInternalRefId);
        properties.setLatitudeOfCenter(featureEllipsis.getCenterlat());
        properties.setLongitudeOfCenter(featureEllipsis.getCenterlon());
        properties.setEllipsisMinorAxis(featureEllipsis.getEllipsisMinorAxis());
        properties.setEllipsisMajorAxis(featureEllipsis.getEllipsisMajorAxis());
        properties.setEllipsisMajorAxisAzimuthAngle(featureEllipsis.getEllipsisMajorAxisAzimuthAngle());

        // Add the Geometry Coordinates to the Feature
        modelTrajectoryFeature.setGeometry(new PointGeometry(modelFeature.getEllipses().get(simulationModelInternalRefId).getCenterlon(), modelFeature.getEllipses().get(simulationModelInternalRefId).getCenterlat()));

        // Return the Feature
        this.getFeatures().add(modelTrajectoryFeature);
    }
}
