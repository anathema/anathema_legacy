package net.sf.anathema.character.main.framework.item;

import net.sf.anathema.framework.repository.IBasicRepositoryIdData;

public interface IItemRepositoryLocation extends IBasicRepositoryIdData {

  void setId(String id);

  String getId();
}