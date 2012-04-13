package net.sf.anathema.lib.resources;

public class ResourceCollection implements IResourceCollection {

	private final IResources resources;
	private final IExtensibleDataSetProvider dataProvider;
	
	public ResourceCollection(IResources resources,
			IExtensibleDataSetProvider dataProvider) {
		this.resources = resources;
		this.dataProvider = dataProvider;
	}
	
	@Override
	public IResources getUIResources() {
		return resources;
	}

	@Override
	public IExtensibleDataSetProvider getDataProvider() {
		return dataProvider;
	}
}
