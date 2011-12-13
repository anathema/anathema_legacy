package net.sf.anathema.lib.container;

import net.sf.anathema.lib.lang.ArrayFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultSelectionContainer<V> implements IGenericSelectionContainer<V> {

  private final List<V> valueList = new ArrayList<V>();
  private final ArrayFactory<V> arrayFactory;
  private final V[] availableValues;

  public DefaultSelectionContainer(Class<V> componentType, V[] availableValues) {
    this.availableValues = availableValues;
    this.arrayFactory = new ArrayFactory<V>(componentType);
  }

  public V[] getAllAvailableValues() {
    return availableValues;
  }

  public void setValues(V[] values) {
    valueList.clear();
    if (values != null) {
      Collections.addAll(valueList, values);
    }
  }

  public V[] getValues() {
    return valueList.toArray(arrayFactory.createArray(valueList.size()));
  }
}