package net.sf.anathema.character.main.framework.item;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.ChangeManagement;

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