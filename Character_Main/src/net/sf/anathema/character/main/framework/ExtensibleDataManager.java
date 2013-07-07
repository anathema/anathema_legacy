package net.sf.anathema.character.main.framework;

import net.sf.anathema.character.main.data.IExtensibleDataSet;
import net.sf.anathema.character.main.data.IExtensibleDataSetProvider;
import net.sf.anathema.character.main.data.IExtensibleDataSetRegistry;

import java.util.ArrayList;
import java.util.List;

public class ExtensibleDataManager implements IExtensibleDataSetProvider, IExtensibleDataSetRegistry {
  private List<IExtensibleDataSet> dataSets = new ArrayList<>();

  @Override
  public void addDataSet(IExtensibleDataSet data) {
    dataSets.add(data);
  }

  @Override
  public <T extends IExtensibleDataSet> T getDataSet(Class<T> setClass) {
    for (IExtensibleDataSet data : dataSets) {
      if (setClass.isInstance(data)) {
        return (T) data;
      }
    }
    throw new IllegalArgumentException("Unknown type of data: " + setClass.getName());
  }
}