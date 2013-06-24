package net.sf.anathema.framework.repository;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.itemdata.model.ItemData;

public interface Item {

  String DEFAULT_PRINT_NAME = "Unnamed";

  ItemData getItemData();

  IItemType getItemType();

  ChangeManagement getChangeManagement();

  IItemRepositoryLocation getRepositoryLocation();

  String getDisplayName();

  void setPrintName(String printName);

  String getId();
}