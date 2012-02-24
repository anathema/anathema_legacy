package net.sf.anathema.framework.repository;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.itemdata.model.IItemData;

public interface IItem extends IChangeManagement {

  String DEFAULT_PRINT_NAME = "Unnamed"; //$NON-NLS-1$

  IItemData getItemData();

  IItemType getItemType();

  IItemRepositoryLocation getRepositoryLocation();

  String getDisplayName();

  void setPrintName(String printName);

  void addItemListener(IItemListener listener);

  void removeItemListener(IItemListener listener);
  
  String getId();
}