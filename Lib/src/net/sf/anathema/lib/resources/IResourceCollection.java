package net.sf.anathema.lib.resources;

import net.sf.anathema.lib.resources.IExtensibleDataSetProvider;
import net.sf.anathema.lib.resources.IResources;

public interface IResourceCollection {
	IResources getUIResources();
	
	IExtensibleDataSetProvider getDataProvider();
}
