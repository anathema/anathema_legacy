package net.sf.anathema.character.generic.data;

public interface IExtensibleDataSetProvider {
	<T extends IExtensibleDataSet> T getDataSet(Class<T> set);
}