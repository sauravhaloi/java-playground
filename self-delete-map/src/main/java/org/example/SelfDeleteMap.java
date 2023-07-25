package org.example;

public interface SelfDeleteMap<K,V> {
    // if there is no entry with the key in the map, add the key/value pair as a new entry
    // if there is an existing entry with the key, the current entry will be replaced the new key/value pair
    // if the newly added entry is not removed after timeOutMs since it is added, it will be removed
    void put(K key, V value, long timeOutMs);

    // get the value associated with the key if present, else return null
    V get(K key);

    // remove the entry associated with the key if any
    void remove(K key);
}
