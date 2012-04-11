package net.sf.anathema.lib.resources;

public interface IExtensibleDataSetProvider {
	<T extends IExtensibleDataSet> T getDataSet(Class<T> set);
}
