package net.sf.anathema.character.framework.data;

public interface IExtensibleDataSetProvider {
	<T extends ExtensibleDataSet> T getDataSet(Class<T> set);
}