package net.sf.anathema.character.main.framework.data;

public interface IExtensibleDataSetProvider {
	<T extends IExtensibleDataSet> T getDataSet(Class<T> set);
}