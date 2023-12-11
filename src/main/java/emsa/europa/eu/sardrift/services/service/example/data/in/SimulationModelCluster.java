package emsa.europa.eu.sardrift.services.service.example.data.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import emsa.europa.eu.sardrift.services.service.example.data.spatial.geom.MutiPointGeometry;

import java.time.ZonedDateTime;
import java.util.List;

public class SimulationModelCluster {

    @JsonProperty("type")
    private String type;

    @JsonProperty("properties")
    private Properties properties;

    @JsonProperty("features")
    private List<Feature> features;

    public String getType() {
        return type;
    }

    public Properties getProperties() {
        return properties;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public static class Properties {

        @JsonProperty("time_step")
        private String timeStep;

        @JsonProperty("start_time")
        private String startTime;

        @JsonProperty("end_time")
        private String endTime;

        @JsonProperty("number_of_times")
        private int numberOfTimes;

        @JsonProperty("model_name")
        private String modelName;

        @JsonProperty("requestID")
        private int requestID;

        @JsonProperty("wind_forcing")
        private String windForcing;

        @JsonProperty("ocean_forcing")
        private String oceanForcing;

        public String getTimeStep() {
            return timeStep;
        }

        public String getStartTime() {
            return startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public int getNumberOfTimes() {
            return numberOfTimes;
        }

        public String getModelName() {
            return modelName;
        }

        public int getRequestID() {
            return requestID;
        }

        public String getWindForcing() {
            return windForcing;
        }

        public String getOceanForcing() {
            return oceanForcing;
        }
    }

    public static class Feature {

        @JsonProperty("time")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX")
        private ZonedDateTime time;

        @JsonProperty("latitude_of_center")
        private double latitudeOfCenter;

        @JsonProperty("longitude_of_center")
        private double longitudeOfCenter;

        @JsonProperty("ellipsis_major_axis")
        private double ellipsisMajorAxis;

        @JsonProperty("ellipsis_minor_axis")
        private double ellipsisMinorAxis;

        @JsonProperty("ellipsis_major_axis_azimuth_angle")
        private double ellipsisMajorAxisAzimuthAngle;

        @JsonProperty("distance_of_center_from_start")
        private double distanceOfCenterFromStart;

        @JsonProperty("azimuth_direction_of_center_from_start")
        private double azimuthDirectionOfCenterFromStart;

        @JsonProperty("geometry")
        private MutiPointGeometry geometry;

        public ZonedDateTime getTime() {
            return time;
        }

        public double getLatitudeOfCenter() {
            return latitudeOfCenter;
        }

        public double getLongitudeOfCenter() {
            return longitudeOfCenter;
        }

        public double getEllipsisMajorAxis() {
            return ellipsisMajorAxis;
        }

        public double getEllipsisMinorAxis() {
            return ellipsisMinorAxis;
        }

        public double getEllipsisMajorAxisAzimuthAngle() {
            return ellipsisMajorAxisAzimuthAngle;
        }

        public double getDistanceOfCenterFromStart() {
            return distanceOfCenterFromStart;
        }

        public double getAzimuthDirectionOfCenterFromStart() {
            return azimuthDirectionOfCenterFromStart;
        }

        public MutiPointGeometry getGeometry() {
            return geometry;
        }
    }
}