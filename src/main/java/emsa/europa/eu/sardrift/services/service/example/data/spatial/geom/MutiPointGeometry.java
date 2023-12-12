package emsa.europa.eu.sardrift.services.service.example.data.spatial.geom;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class MutiPointGeometry implements Geometry<List<Double>> {

    @JsonProperty("type")
    private final String type = "MultiPoint";

    @JsonProperty("coordinates")
    private List<List<Double>> coordinates = new ArrayList<List<Double>>();

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public List<List<Double>> getCoordinates() {
        return this.coordinates;
    }

    @Override
    public void setCoordinates(List<List<Double>> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        // Your custom implementation of toString
        String sb = "{\n" +
                "    \"type\": \"" + type + "\",\n" +
                "\t\"coordinates\": " + getCoordinatesAsString() + "\n" +
                "}";
        return sb;
    }

    private String getCoordinatesAsString() {
        List<List<Double>> coordinates = getCoordinates();
        StringBuilder coordinatesString = new StringBuilder("[");
        for (int i = 0; i < coordinates.size(); i++) {
            coordinatesString.append("[");
            List<Double> point = coordinates.get(i);
            for (int j = 0; j < point.size(); j++) {
                coordinatesString.append(point.get(j));
                if (j < point.size() - 1) {
                    coordinatesString.append(", ");
                }
            }
            coordinatesString.append("]");
            if (i < coordinates.size() - 1) {
                coordinatesString.append(", ");
            }
        }
        coordinatesString.append("]");
        return coordinatesString.toString();
    }
}
