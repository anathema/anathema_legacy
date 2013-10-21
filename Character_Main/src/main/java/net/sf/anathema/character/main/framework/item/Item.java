package net.sf.anathema.character.main.framework.item;

import net.sf.anathema.framework.repository.ChangeManagement;

public interface Item {

  ItemData getItemData();

  ChangeManagement getChangeManagement();

  ItemRepositoryLocation getRepositoryLocation();
}