package emsa.europa.eu.sardrift.services.service.example.data.spatial.geom;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class PointGeometry implements Geometry<Double> {

    @JsonProperty("type")
    private final String type = "Point";

    @JsonProperty("coordinates")
    private List<Double> coordinates = new ArrayList<Double>();

    public PointGeometry(double centerlon, double centerlat) {
        this.coordinates.add(centerlon);
        this.coordinates.add(centerlat);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public List<Double> getCoordinates() {
        return coordinates;
    }

    @Override
    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");
        stringBuilder.append("    \"type\": \"").append(type).append("\",\n");
        stringBuilder.append("    \"coordinates\": [\n");

        for (int i = 0; i < coordinates.size(); i++) {
            stringBuilder.append("        ").append(coordinates.get(i));
            if (i < coordinates.size() - 1) {
                stringBuilder.append(",");
            }
            stringBuilder.append("\n");
        }

        stringBuilder.append("    ]\n");
        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}
