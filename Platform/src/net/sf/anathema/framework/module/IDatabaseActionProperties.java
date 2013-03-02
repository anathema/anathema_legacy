package net.sf.anathema.framework.module;

import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.initialization.repository.IDataFileProvider;

import javax.swing.Icon;

public interface IDatabaseActionProperties {

  String getItemTypeId();

  String getActionName();

  Icon getActionIcon();

  String getToolTipText();

  String getProgressTaskTitle();

  String getItemId();

  IItemData createItemData(IDataFileProvider provider);
}