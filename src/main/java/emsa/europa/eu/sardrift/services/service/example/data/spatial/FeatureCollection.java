package emsa.europa.eu.sardrift.services.service.example.data.spatial;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import emsa.europa.eu.sardrift.services.service.example.data.spatial.geom.Geometry;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class FeatureCollection {

    @JsonProperty("type")
    private final String type = "FeatureCollection";

    public String getType() {
        return type;
    }

    @JsonProperty("features")
    private final List<Feature> features = new ArrayList<Feature>();

    public List<Feature> getFeatures() {
        return features;
    }

    public static class Feature {

        @JsonProperty("type")
        private final String type = "Feature";

        @JsonProperty("properties")
        private Properties properties;

        @JsonProperty("geometry")
        private Geometry geometry;

        public String getType() {
            return type;
        }

        public Properties getProperties() {
            return properties;
        }

        public void setProperties(Properties properties) {
            this.properties = properties;
        }

        public Geometry getGeometry() {
            return geometry;
        }

        public void setGeometry(Geometry geometry) {
            this.geometry = geometry;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Properties {

        @JsonProperty("time_step")
        private String timeStep;

        @JsonProperty("start_time")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX")
        private OffsetDateTime startTime;

        @JsonProperty("end_time")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX")
        private OffsetDateTime endTime;

        @JsonProperty("number_of_times")
        private int numberOfTimes;

        @JsonProperty("model_name")
        private String modelName;

        @JsonProperty("request_id")
        private int requestID;

        @JsonProperty("simulation")
        private String simulation;

        @JsonProperty("wind_forcing")
        private String windForcing;

        @JsonProperty("ocean_forcing")
        private String oceanForcing;

        @JsonProperty("time")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX")
        private OffsetDateTime time;

        @JsonProperty("latitude_of_center")
        private Double latitudeOfCenter;

        @JsonProperty("longitude_of_center")
        private Double longitudeOfCenter;

        @JsonProperty("ellipsis_major_axis")
        private Double ellipsisMajorAxis;

        @JsonProperty("ellipsis_minor_axis")
        private Double ellipsisMinorAxis;

        @JsonProperty("ellipsis_major_axis_azimuth_angle")
        private Double ellipsisMajorAxisAzimuthAngle;

        @JsonProperty("distance_of_center_from_start")
        private Double distanceOfCenterFromStart;

        @JsonProperty("azimuth_direction_of_center_from_start")
        private Double azimuthDirectionOfCenterFromStart;

        public Double getBboxLonMin() {
            return bboxLonMin;
        }

        public void setBboxLonMin(Double bboxLonMin) {
            this.bboxLonMin = bboxLonMin;
        }

        public Double getBboxLonMax() {
            return bboxLonMax;
        }

        public void setBboxLonMax(Double bboxLonMax) {
            this.bboxLonMax = bboxLonMax;
        }

        public Double getBboxLatMin() {
            return bboxLatMin;
        }

        public void setBboxLatMin(Double bboxLatMin) {
            this.bboxLatMin = bboxLatMin;
        }

        public Double getBboxLatMax() {
            return bboxLatMax;
        }

        public void setBboxLatMax(Double bboxLatMax) {
            this.bboxLatMax = bboxLatMax;
        }

        public Double getBboxCenterLon() {
            return bboxCenterLon;
        }

        public void setBboxCenterLon(Double bboxCenterLon) {
            this.bboxCenterLon = bboxCenterLon;
        }

        public Double getBboxCenterLat() {
            return bboxCenterLat;
        }

        public void setBboxCenterLat(Double bboxCenterLat) {
            this.bboxCenterLat = bboxCenterLat;
        }

        public Double getSuperEllipsisCenterLon() {
            return superEllipsisCenterLon;
        }

        public void setSuperEllipsisCenterLon(Double superEllipsisCenterLon) {
            this.superEllipsisCenterLon = superEllipsisCenterLon;
        }

        public Double getSuperEllipsisCenterLat() {
            return superEllipsisCenterLat;
        }

        public void setSuperEllipsisCenterLat(Double superEllipsisCenterLat) {
            this.superEllipsisCenterLat = superEllipsisCenterLat;
        }

        public Double getSuperEllipsisMajorAxis() {
            return superEllipsisMajorAxis;
        }

        public void setSuperEllipsisMajorAxis(Double superEllipsisMajorAxis) {
            this.superEllipsisMajorAxis = superEllipsisMajorAxis;
        }

        public Double getSuperEllipsisMinorAxis() {
            return superEllipsisMinorAxis;
        }

        public void setSuperEllipsisMinorAxis(Double superEllipsisMinorAxis) {
            this.superEllipsisMinorAxis = superEllipsisMinorAxis;
        }

        public Double getSuperEllipsisMajorAxisAzimuthAngle() {
            return superEllipsisMajorAxisAzimuthAngle;
        }

        public void setSuperEllipsisMajorAxisAzimuthAngle(Double superEllipsisMajorAxisAzimuthAngle) {
            this.superEllipsisMajorAxisAzimuthAngle = superEllipsisMajorAxisAzimuthAngle;
        }

        @JsonProperty("bbox_lonmin")
        private Double bboxLonMin;

        @JsonProperty("bbox_lonmax")
        private Double bboxLonMax;

        @JsonProperty("bbox_latmin")
        private Double bboxLatMin;

        @JsonProperty("bbox_latmax")
        private Double bboxLatMax;

        @JsonProperty("bbox_centerlon")
        private Double bboxCenterLon;

        @JsonProperty("bbox_centerlat")
        private Double bboxCenterLat;

        @JsonProperty("super_ellipsis_centerlon")
        private Double superEllipsisCenterLon;

        @JsonProperty("super_ellipsis_centerlat")
        private Double superEllipsisCenterLat;

        @JsonProperty("super_ellipsis_major_axis")
        private Double superEllipsisMajorAxis;

        @JsonProperty("super_ellipsis_minor_axis")
        private Double superEllipsisMinorAxis;

        @JsonProperty("super_ellipsis_major_axis_azimuth_angle")
        private Double superEllipsisMajorAxisAzimuthAngle;

        public String getTimeStep() {
            return timeStep;
        }

        public void setTimeStep(String timeStep) {
            this.timeStep = timeStep;
        }

        public OffsetDateTime getStartTime() {
            return startTime;
        }

        public void setStartTime(OffsetDateTime startTime) {
            this.startTime = startTime;
        }

        public OffsetDateTime getEndTime() {
            return endTime;
        }

        public void setEndTime(OffsetDateTime endTime) {
            this.endTime = endTime;
        }

        public int getNumberOfTimes() {
            return numberOfTimes;
        }

        public void setNumberOfTimes(int numberOfTimes) {
            this.numberOfTimes = numberOfTimes;
        }

        public String getModelName() {
            return modelName;
        }

        public void setModelName(String modelName) {
            this.modelName = modelName;
        }

        public int getRequestID() {
            return requestID;
        }

        public String getSimulation() {
            return simulation;
        }

        public void setSimulation(String simulation) {
            this.simulation = simulation;
        }

        public void setRequestID(int requestID) {
            this.requestID = requestID;
        }

        public String getWindForcing() {
            return windForcing;
        }

        public void setWindForcing(String windForcing) {
            this.windForcing = windForcing;
        }

        public String getOceanForcing() {
            return oceanForcing;
        }

        public void setOceanForcing(String oceanForcing) {
            this.oceanForcing = oceanForcing;
        }

        public OffsetDateTime getTime() {
            return time;
        }

        public void setTime(OffsetDateTime time) {
            this.time = time;
        }

        public Double getLatitudeOfCenter() {
            return latitudeOfCenter;
        }

        public void setLatitudeOfCenter(Double latitudeOfCenter) {
            this.latitudeOfCenter = latitudeOfCenter;
        }

        public Double getLongitudeOfCenter() {
            return longitudeOfCenter;
        }

        public void setLongitudeOfCenter(Double longitudeOfCenter) {
            this.longitudeOfCenter = longitudeOfCenter;
        }

        public Double getEllipsisMajorAxis() {
            return ellipsisMajorAxis;
        }

        public void setEllipsisMajorAxis(Double ellipsisMajorAxis) {
            this.ellipsisMajorAxis = ellipsisMajorAxis;
        }

        public Double getEllipsisMinorAxis() {
            return ellipsisMinorAxis;
        }

        public void setEllipsisMinorAxis(Double ellipsisMinorAxis) {
            this.ellipsisMinorAxis = ellipsisMinorAxis;
        }

        public Double getEllipsisMajorAxisAzimuthAngle() {
            return ellipsisMajorAxisAzimuthAngle;
        }

        public void setEllipsisMajorAxisAzimuthAngle(Double ellipsisMajorAxisAzimuthAngle) {
            this.ellipsisMajorAxisAzimuthAngle = ellipsisMajorAxisAzimuthAngle;
        }

        public Double getDistanceOfCenterFromStart() {
            return distanceOfCenterFromStart;
        }

        public void setDistanceOfCenterFromStart(Double distanceOfCenterFromStart) {
            this.distanceOfCenterFromStart = distanceOfCenterFromStart;
        }

        public Double getAzimuthDirectionOfCenterFromStart() {
            return azimuthDirectionOfCenterFromStart;
        }

        public void setAzimuthDirectionOfCenterFromStart(Double azimuthDirectionOfCenterFromStart) {
            this.azimuthDirectionOfCenterFromStart = azimuthDirectionOfCenterFromStart;
        }
    }
}
