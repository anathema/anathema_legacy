package net.sf.anathema.character.main.data;

public interface IExtensibleDataSetProvider {
	<T extends IExtensibleDataSet> T getDataSet(Class<T> set);
}