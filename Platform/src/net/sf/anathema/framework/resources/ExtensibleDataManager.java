package net.sf.anathema.framework.resources;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.lib.resources.IExtensibleDataSet;
import net.sf.anathema.lib.resources.IExtensibleDataSetProvider;

public class ExtensibleDataManager implements IExtensibleDataSetProvider {
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
