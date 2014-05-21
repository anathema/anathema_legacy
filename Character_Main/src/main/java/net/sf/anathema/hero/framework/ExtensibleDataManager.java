package net.sf.anathema.hero.framework;

import net.sf.anathema.character.framework.data.ExtensibleDataSet;
import net.sf.anathema.character.framework.data.IExtensibleDataSetProvider;
import net.sf.anathema.character.framework.data.IExtensibleDataSetRegistry;

import java.util.ArrayList;
import java.util.List;

public class ExtensibleDataManager implements IExtensibleDataSetProvider, IExtensibleDataSetRegistry {
  private List<ExtensibleDataSet> dataSets = new ArrayList<>();

  @Override
  public void addDataSet(ExtensibleDataSet data) {
    dataSets.add(data);
  }

  @Override
  public <T extends ExtensibleDataSet> T getDataSet(Class<T> setClass) {
    for (ExtensibleDataSet data : dataSets) {
      if (setClass.isInstance(data)) {
        return (T) data;
      }
    }
    throw new IllegalArgumentException("Unknown type of data: " + setClass.getName());
  }
}