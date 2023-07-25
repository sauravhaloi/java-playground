package org.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SelfDeleteMap implementation with Memory optimized but CPU inefficient
 * In the put method, the keys are deleted if they are expired
 * @param <K>
 * @param <V>
 */

public class SdmMemOptimized<K, V> implements SelfDeleteMap<K,V> {

    private final Map<K, Tuple<V, Long>> map = new ConcurrentHashMap<>();

    @Override
    public void put(K key, V value, long timeOutMs) {

        // remove if exists
        map.remove(key);

        // Inefficient implementation
        // A better implementation could have been to have a separate background thread
        // that runs periodically and remove the expired keys
        clearExpiredKeys();

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

    private void clearExpiredKeys() {
        for (K key : map.keySet()) {
            Tuple<V, Long> tuple = map.get(key);
            if (isExpired(tuple.item2())) {
                remove(key);
            }
        }
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