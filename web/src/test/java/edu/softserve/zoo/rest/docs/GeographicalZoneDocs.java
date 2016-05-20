package edu.softserve.zoo.rest.docs;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static edu.softserve.zoo.controller.rest.Routes.GEO_ZONE;

/**
 * @author Taras Zubrei
 */
public class GeographicalZoneDocs extends AbstractCrud{
    @Test
    public void run() throws Exception {
        Map<String, Object> zone = new HashMap<String, Object>() {{
            put("regionName", "Wakanda");
        }};

        getOp();
        postOp(zone);
        zone.put("regionName", "Sokovia");
        patchOp(zone, 15L);
        deleteOp(15L);
    }
    @Override
    protected String getPath() {
        return GEO_ZONE;
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
