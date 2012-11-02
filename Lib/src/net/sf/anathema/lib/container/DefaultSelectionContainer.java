package net.sf.anathema.lib.container;

import net.sf.anathema.lib.lang.ArrayFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultSelectionContainer<V> implements IGenericSelectionContainer<V> {

  private final List<V> valueList = new ArrayList<>();
  private final ArrayFactory<V> arrayFactory;
  private final V[] availableValues;

  public DefaultSelectionContainer(Class<V> componentType, V[] availableValues) {
    this.availableValues = availableValues;
    this.arrayFactory = new ArrayFactory<>(componentType);
  }

  @Override
  public V[] getAllAvailableValues() {
    return availableValues;
  }

  @Override
  public void setValues(V[] values) {
    valueList.clear();
    if (values != null) {
      Collections.addAll(valueList, values);
    }
  }

  @Override
  public V[] getValues() {
    return valueList.toArray(arrayFactory.createArray(valueList.size()));
  }
}