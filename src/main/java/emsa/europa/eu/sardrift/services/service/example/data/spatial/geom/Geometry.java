package emsa.europa.eu.sardrift.services.service.example.data.spatial.geom;

import java.util.List;

public interface Geometry<E> {

    String getType();

    List<E> getCoordinates();

    void setCoordinates(List<E> coordinates);
}
