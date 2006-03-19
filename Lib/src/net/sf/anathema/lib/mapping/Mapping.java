package net.sf.anathema.lib.mapping;

public class Mapping<K, V> {
  
  private final K key;
  private final V value;

  public Mapping(K key, V value) {
    this.key = key;
    this.value = value;
  }
  
  public K getKey() {
    return key;
  }
  
  public V getValue() {
    return value;
  }
}