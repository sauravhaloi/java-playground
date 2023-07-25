package org.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SelfDeleteMap implementation with CPU optimized but Memory inefficient
 * The keys are not deleted until they are accessed and found to be expired
 * @param <K>
 * @param <V>
 */
public class SdmCpuOptimized<K, V> implements SelfDeleteMap<K,V> {

    private final Map<K, Tuple<V, Long>> map = new ConcurrentHashMap<>();

    @Override
    public void put(K key, V value, long timeOutMs) {

        // remove if exists
        map.remove(key);

        Tuple <V, Long> tuple = new Tuple<>(value, System.currentTimeMillis() + timeOutMs);
        map.put(key, tuple);
    }

    @Override
    public V get(K key) {
        Tuple<V, Long> tuple = map.get(key);

        if (tuple == null) {
            return null;
        }

        V value = tuple.item1();
        Long timeOutMs = tuple.item2();

        if (isExpired(timeOutMs)) {
            remove(key);
            return null;
        }

        return value;
    }

    @Override
    public void remove(K key) {
        map.remove(key);
    }

    private boolean isExpired(Long timeOutMs) {
        return timeOutMs <= System.currentTimeMillis();
    }

    public void deleteAll() {
        for (K key : map.keySet()) {
            remove(key);
        }
    }

    public int size() {
        return map.size();
    }
}