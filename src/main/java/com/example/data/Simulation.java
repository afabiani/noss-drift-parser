package com.example.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class Simulation {

    @JsonProperty("type")
    private String type;

    @JsonProperty("simulations")
    private Map<String, String> simulations;

    @JsonProperty("coverage")
    private Coverage coverage;

    @JsonProperty("features")
    private List<Feature> features;

    public String getType() {
        return type;
    }

    public Map<String, String> getSimulations() {
        return simulations;
    }

    public Coverage getCoverage() {
        return coverage;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public static class Coverage {

        @JsonProperty("lonmin")
        private double lonmin;

        @JsonProperty("lonmax")
        private double lonmax;

        @JsonProperty("latmin")
        private double latmin;

        @JsonProperty("latmax")
        private double latmax;

        @JsonProperty("centerlon")
        private double centerlon;

        @JsonProperty("centerlat")
        private double centerlat;

        public double getLonmin() {
            return lonmin;
        }

        public double getLonmax() {
            return lonmax;
        }

        public double getLatmin() {
            return latmin;
        }

        public double getLatmax() {
            return latmax;
        }

        public double getCenterlon() {
            return centerlon;
        }

        public double getCenterlat() {
            return centerlat;
        }
    }
}
