package emsa.europa.eu.sardrift.services.service.example.data.spatial.geom;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class MutiPointGeometry implements Geometry<List<Double>> {

    @JsonProperty("type")
    private String type = "MultiPoint";

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
}
