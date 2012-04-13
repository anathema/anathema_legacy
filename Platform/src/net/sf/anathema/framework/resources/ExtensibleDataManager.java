package net.sf.anathema.framework.resources;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.lib.resources.IExtensibleDataSet;
import net.sf.anathema.lib.resources.IExtensibleDataSetProvider;
import net.sf.anathema.lib.resources.IExtensibleDataSetRegistry;

public class ExtensibleDataManager implements IExtensibleDataSetProvider, IExtensibleDataSetRegistry {
	private List<IExtensibleDataSet> dataSets = new ArrayList<IExtensibleDataSet>();
	
	public void addDataSet(IExtensibleDataSet data) {
		dataSets.add(data);
	}
	
	public <T extends IExtensibleDataSet> T getDataSet(Class<T> setClass) {
		for (IExtensibleDataSet data : dataSets) {
			if (setClass.isInstance(data)) {
				return (T) data;
			}
		}
		return null;
	}
}
