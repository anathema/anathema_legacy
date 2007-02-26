package net.sf.anathema.lib.compare;

import net.disy.commons.core.util.ObjectUtilities;

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
    if (!(obj instanceof WeightedObject)) {
      return false;
    }
    WeightedObject< ? > weightedObject = (WeightedObject< ? >) obj;
    return weight == weightedObject.getWeight() && ObjectUtilities.equals(value, weightedObject.getValue());
  }

  @Override
  public int hashCode() {
    return weight;
  }

  public int compareTo(WeightedObject<V> o) {
    return weight - o.weight;
  }
}