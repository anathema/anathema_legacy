package net.sf.anathema.hero.framework.data;

public interface IExtensibleDataSetProvider {
	<T extends ExtensibleDataSet> T getDataSet(Class<T> set);
}