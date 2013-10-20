package net.sf.anathema.character.main.framework.item;

import net.sf.anathema.framework.repository.BasicRepositoryIdData;

public interface ItemRepositoryLocation extends BasicRepositoryIdData {

  void setId(String id);

  String getId();
}