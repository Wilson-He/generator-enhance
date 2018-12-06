package per.wilson.generator.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * MapUtils
 *
 * @author Wilson
 * @since 18-4-16
 */
public class MapUtils {

    private Map<String, Object> map;

    public MapUtils() {
    }

    private MapUtils(int initCapacity) {
        this.map = new HashMap<>(initCapacity);
    }

    private MapUtils(Map<String, Object> map) {
        this.map = map;
    }

    public static MapUtils build(int initCapacity) {
        return new MapUtils(initCapacity);
    }

    public static MapUtils build(Map<String, Object> map) {
        return new MapUtils(map);
    }

    public static MapUtils build() {
        return new MapUtils();
    }

    public MapUtils put(String key, Object value) {
        map.put(key, value);
        return this;
    }

    public Map<String, Object> map() {
        return this.map;
    }
}
