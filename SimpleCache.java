package com.example.apod.cache;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

// Small LRU cache with TTL using LinkedHashMap
public class SimpleCache<K, V> {
private final long ttlMs;
private final int maxSize;
private final Map<K, CacheItem<V>> map;

public SimpleCache(long ttlMs, int maxSize) {
this.ttlMs = ttlMs;
this.maxSize = maxSize;
this.map = new LinkedHashMap<>(16, 0.75f, true) {
@Override
protected boolean removeEldestEntry(Map.Entry<K, CacheItem<V>> eldest) {
return size() > SimpleCache.this.maxSize;
}
};
}
public synchronized V get(K key) {
CacheItem<V> item = map.get(key);
if (item == null) return null;
if (Instant.now().toEpochMilli() > item.expiry) {
map.remove(key);
return null;
}
return item.value;
}

public synchronized void put(K key, V value) {
map.put(key, new CacheItem<>(value, Instant.now().toEpochMilli() + ttlMs));
}

private static class CacheItem<V> {
final V value;
final long expiry;
CacheItem(V value, long expiry) { this.value = value; this.expiry = expiry; }
}
}
