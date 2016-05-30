package edu.softserve.zoo.rest.docs;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static edu.softserve.zoo.controller.rest.Routes.GEO_ZONES;

/**
 * @author Taras Zubrei
 */
public class GeographicalZoneDocs extends AbstractCrud {
    @Test
    public void run() throws Exception {
        getOp();
    }

    @Override
    protected String getPath() {
        return GEO_ZONES;
    }

    @Override
    protected Map<String, String> getFieldsWithDescription() {
        return new LinkedHashMap<String, String>() {{
            put("id", "id of geozone");
            put("regionName", "name of the geozone region");
        }};
    }

    @Override
    protected String getName() {
        return "GeoZone";
    }
}
