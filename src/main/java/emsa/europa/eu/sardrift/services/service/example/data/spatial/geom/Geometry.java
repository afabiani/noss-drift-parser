package emsa.europa.eu.sardrift.services.service.example.data.spatial.geom;

import java.util.List;

public interface Geometry<E> {

    public String getType();

    public List<E> getCoordinates();

    public void setCoordinates(List<E> coordinates);
}
