package emsa.europa.eu.sardrift.services.service.example.data.spatial.geom;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class PointGeometry implements Geometry<Double> {

    @JsonProperty("type")
    private String type = "Point";

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
}
