package org.xquery.saxon.coverage.util;

import java.util.Map;

public final class MapUtils {

    private MapUtils() {
    }

    public static <K, V> V putIfAbsent(Map<K, V> map, K key, V value) {
        V actualValue = map.get(key);
        if (actualValue == null) {
            actualValue = value;
            map.put(key, actualValue);
        }
        return actualValue;
    }
}
