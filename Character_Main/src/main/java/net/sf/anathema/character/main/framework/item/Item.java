package net.sf.anathema.character.main.framework.item;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.ChangeManagement;

public interface Item {

  ItemData getItemData();

  IItemType getItemType();

  ChangeManagement getChangeManagement();

  ItemRepositoryLocation getRepositoryLocation();

  String getDisplayName();

  void setPrintName(String printName);

  String getId();
}