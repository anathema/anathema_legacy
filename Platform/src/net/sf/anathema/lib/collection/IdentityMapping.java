package net.sf.anathema.lib.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IdentityMapping<K, V> {

  private final List<KeyValueMapping<K, V>> mappings = new ArrayList<>();

  public void put(K key, V value) {
    if (get(key) != null) {
      throw new IllegalArgumentException("Key " + key + " already mapped");
    }
    mappings.add(new KeyValueMapping<>(key, value));
  }

  public List<K> getAllKeys() {
    List<K> list = new ArrayList<>();
    for (KeyValueMapping<K, V> mapping : mappings) {
      list.add(mapping.getKey());
    }
    return list;
  }

  public V get(K key) {
    for (KeyValueMapping<K, V> mapping : mappings) {
      if (key == mapping.getKey()) {
        return mapping.getValue();
      }
    }
    return null;
  }

  public void remove(K key) {
    for (Iterator<KeyValueMapping<K, V>> mappingInterator = mappings.iterator(); mappingInterator.hasNext();) {
      KeyValueMapping<K, V> mapping = mappingInterator.next();
      if (key == mapping.getKey()) {
        mappingInterator.remove();
      }
    }
  }

  private static class KeyValueMapping<Key, Value> {
    private final Key key;
    private final Value value;

    public KeyValueMapping(Key key, Value value) {
      this.key = key;
      this.value = value;
    }

    public Key getKey() {
      return key;
    }

    public Value getValue() {
      return value;
    }
  }
}