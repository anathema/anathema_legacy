package net.sf.anathema.lib.compare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class WeightedObjectSorter<V> {

  public abstract WeightedObject<V>[] convertToArray(Collection<WeightedObject<V>> collection);

  public abstract WeightedObject<V> createWeightedObject(V value, int weight);

  private WeightedObject<V>[] getAscendingArray(V[] values, int[] weights) {
    List<WeightedObject<V>> weightedList = new ArrayList<WeightedObject<V>>();
    for (int index = 0; index < values.length; index++) {
      V nextValue = values[index];
      weightedList.add(createWeightedObject(nextValue, weights[index]));
    }
    WeightedObject<V>[] weightedArray = convertToArray(weightedList);
    Arrays.sort(weightedArray);
    return weightedArray;
  }

  public List<V> sortAscending(V[] values, int[] weights) {
    WeightedObject<V>[] weightedArray = getAscendingArray(values, weights);
    List<V> sortedList = new ArrayList<V>();
    for (WeightedObject<V> element : weightedArray) {
      sortedList.add(element.getValue());
    }
    return sortedList;
  }

  public List<V> sortDescending(V[] values, int[] weights) {
    WeightedObject<V>[] weightedArray = getAscendingArray(values, weights);
    List<V> sortedList = new ArrayList<V>();
    for (int index = weightedArray.length - 1; index > -1; index--) {
      sortedList.add(weightedArray[index].getValue());
    }
    return sortedList;
  }
}