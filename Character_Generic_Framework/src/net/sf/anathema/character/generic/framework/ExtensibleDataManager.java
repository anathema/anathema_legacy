package net.sf.anathema.character.generic.framework;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.lib.resources.IExtensibleDataSet;
import net.sf.anathema.lib.resources.IExtensibleDataSetProvider;
import net.sf.anathema.lib.resources.IExtensibleDataSetRegistry;

public class ExtensibleDataManager implements IExtensibleDataSetProvider, IExtensibleDataSetRegistry {
  private List<IExtensibleDataSet> dataSets = new ArrayList<IExtensibleDataSet>();

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