package net.sf.anathema.lib.compare;

import com.google.common.base.Objects;

public class WeightedObject<V> implements Comparable<WeightedObject<V>> {

  private final V value;
  private final int weight;

  public WeightedObject(V value, int weight) {
    this.value = value;
    this.weight = weight;
  }

  public int getWeight() {
    return weight;
  }

  public V getValue() {
    return value;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof WeightedObject<?>)) {
      return false;
    }
    WeightedObject< ? > weightedObject = (WeightedObject< ? >) obj;
    return weight == weightedObject.getWeight() && Objects.equal(value, weightedObject.getValue());
  }

  @Override
  public int hashCode() {
    return weight;
  }

  public int compareTo(WeightedObject<V> o) {
    return weight - o.weight;
  }
}