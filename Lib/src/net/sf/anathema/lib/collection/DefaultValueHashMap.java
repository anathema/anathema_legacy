package net.sf.anathema.lib.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/** HashMap that returns the set default value instead of null. */
public class DefaultValueHashMap<K, V> implements Map<K, V> {

  private final Map<K, V> map = new HashMap<K, V>();
  private final V defaultValue;

  public DefaultValueHashMap() {
    defaultValue = null;
  }

  public DefaultValueHashMap(V defaultValue) {
    this.defaultValue = defaultValue;
  }

  public void clear() {
    map.clear();
  }

  public boolean containsKey(Object key) {
    return map.containsKey(key);
  }

  public boolean containsValue(Object value) {
    return map.containsValue(value);
  }

  public Set<java.util.Map.Entry<K, V>> entrySet() {
    return map.entrySet();
  }

  public V get(Object key) {
    V v = map.get(key);
    if (v == null) {
      return defaultValue;
    }
    return v;
  }

  public boolean isEmpty() {
    return map.isEmpty();
  }

  public Set<K> keySet() {
    return map.keySet();
  }

  public V put(K key, V value) {
    return map.put(key, value);
  }

  public void putAll(Map< ? extends K, ? extends V> m) {
    map.putAll(m);
  }

  public V remove(Object key) {
    return map.remove(key);
  }

  public int size() {
    return map.size();
  }

  public Collection<V> values() {
    return map.values();
  }
}