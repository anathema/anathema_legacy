package net.sf.anathema.framework.module;

import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.initialization.repository.IDataFileProvider;

public interface IDatabaseActionProperties {

  String getToolTipText();

  IItemData createItemData(IDataFileProvider provider);
}