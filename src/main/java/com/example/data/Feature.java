package com.example.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.time.ZonedDateTime;
import java.util.Map;

public class Feature {

    @JsonProperty("time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    private ZonedDateTime time;

    @JsonProperty("super-ellipse")
    private SuperEllipse superEllipse;

    @JsonProperty("ellipses")
    private Map<String, Ellipse> ellipses;

    @JsonProperty("clusters")
    private Map<String, Cluster> clusters;

    public ZonedDateTime getTime() {
        return time;
    }

    public SuperEllipse getSuperEllipse() {
        return superEllipse;
    }

    public Map<String, Ellipse> getEllipses() {
        return ellipses;
    }

    public Map<String, Cluster> getClusters() {
        return clusters;
    }

    public static class SuperEllipse {

        @JsonProperty("centerlon")
        private double centerlon;

        @JsonProperty("centerlat")
        private double centerlat;

        @JsonProperty("ellipsis_major_axis")
        private double ellipsisMajorAxis;

        @JsonProperty("ellipsis_minor_axis")
        private double ellipsisMinorAxis;

        @JsonProperty("ellipsis_major_axis_azimuth_angle")
        private double ellipsisMajorAxisAzimuthAngle;

        public double getCenterlon() {
            return centerlon;
        }

        public double getCenterlat() {
            return centerlat;
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
    }

    public static class Ellipse {

        @JsonProperty("ellipsis_major_axis")
        private double ellipsisMajorAxis;

        @JsonProperty("ellipsis_minor_axis")
        private double ellipsisMinorAxis;

        @JsonProperty("centerlon")
        private double centerlon;

        @JsonProperty("centerlat")
        private double centerlat;

        @JsonProperty("ellipsis_major_axis_azimuth_angle")
        private double ellipsisMajorAxisAzimuthAngle;

        public double getEllipsisMajorAxis() {
            return ellipsisMajorAxis;
        }

        public double getEllipsisMinorAxis() {
            return ellipsisMinorAxis;
        }

        public double getCenterlon() {
            return centerlon;
        }

        public double getCenterlat() {
            return centerlat;
        }

        public double getEllipsisMajorAxisAzimuthAngle() {
            return ellipsisMajorAxisAzimuthAngle;
        }
    }

    public static class Cluster {

        @JsonProperty("members")
        private int[] members;

        @JsonProperty("memberlons")
        private double[] memberlons;

        @JsonProperty("memberlats")
        private double[] memberlats;

        @JsonProperty("centerlon")
        private double centerlon;

        @JsonProperty("centerlat")
        private double centerlat;

        @JsonProperty("distance_from_cluster_centre")
        private double[] distanceFromClusterCentre;

        @JsonProperty("distance_std")
        private double distanceStd;

        @JsonProperty("longest_ellipsis_axis")
        private double longestEllipseAxis;

        public int[] getMembers() {
            return members;
        }

        public double[] getMemberlons() {
            return memberlons;
        }

        public double[] getMemberlats() {
            return memberlats;
        }

        public double getCenterlon() {
            return centerlon;
        }

        public double getCenterlat() {
            return centerlat;
        }

        public double[] getDistanceFromClusterCentre() {
            return distanceFromClusterCentre;
        }

        public double getDistanceStd() {
            return distanceStd;
        }

        public double getLongestEllipseAxis() {
            return longestEllipseAxis;
        }
    }
}
