package net.sf.anathema.framework.module;

import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.initialization.repository.DataFileProvider;

public interface IDatabaseActionProperties {

  String getToolTipText();

  IItemData createItemData(DataFileProvider provider);
}