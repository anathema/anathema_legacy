package net.sf.anathema.framework.repository;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.itemdata.model.ItemData;

public interface Item extends ChangeManagement {

  String DEFAULT_PRINT_NAME = "Unnamed";

  ItemData getItemData();

  IItemType getItemType();

  IItemRepositoryLocation getRepositoryLocation();

  String getDisplayName();

  void setPrintName(String printName);

  void addItemListener(IItemListener listener);

  void removeItemListener(IItemListener listener);
  
  String getId();
}