package net.sf.anathema.framework.resources;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.lib.resources.IExtensibleDataSet;

public class ExtensibleDataManager {
	private List<IExtensibleDataSet> dataSets = new ArrayList<IExtensibleDataSet>();
	
	public void addDataSet(IExtensibleDataSet data) {
		dataSets.add(data);
	}
	
	public IExtensibleDataSet getDataSet(String id) {
		for (IExtensibleDataSet data : dataSets) {
			if (data.getId().equals(id)) {
				return data;
			}
		}
		return null;
	}
}
