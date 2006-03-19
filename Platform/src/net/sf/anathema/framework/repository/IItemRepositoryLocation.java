package net.sf.anathema.framework.repository;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.lib.util.IIdentificate;

public interface IItemRepositoryLocation extends IIdentificate {

  public String getIdProposal();

  public IItemType getItemType();

  public void setId(String id);
}