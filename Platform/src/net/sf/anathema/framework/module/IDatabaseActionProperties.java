package net.sf.anathema.framework.module;

import net.sf.anathema.framework.itemdata.model.ItemData;
import net.sf.anathema.initialization.repository.DataFileProvider;

public interface IDatabaseActionProperties {

  String getToolTipText();

  ItemData createItemData(DataFileProvider provider);
}