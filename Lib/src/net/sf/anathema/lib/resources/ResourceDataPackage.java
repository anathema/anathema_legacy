package net.sf.anathema.lib.resources;

public class ResourceDataPackage implements IResourceDataManager {

	private final IResources resources;
	private final IExtensibleDataSetProvider dataProvider;
	private final IExtensibleDataSetRegistry dataRegistry;
	
	public ResourceDataPackage(IResources resources,
			IExtensibleDataSetProvider dataProvider) {
		this(resources, dataProvider, null);
	}
	
	public ResourceDataPackage(IResources resources,
			IExtensibleDataSetProvider dataProvider,
			IExtensibleDataSetRegistry dataRegistry) {
		this.resources = resources;
		this.dataProvider = dataProvider;
		this.dataRegistry = dataRegistry;
	}
	
	@Override
	public IResources getUIResources() {
		return resources;
	}

	@Override
	public IExtensibleDataSetProvider getDataProvider() {
		return dataProvider;
	}

	@Override
	public IExtensibleDataSetRegistry getDataSetRegistry() {
		return dataRegistry;
	}
	
}
