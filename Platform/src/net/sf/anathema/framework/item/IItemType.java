package net.sf.anathema.framework.item;

import net.sf.anathema.lib.util.IIdentificate;

public interface IItemType extends IIdentificate {

  public IRepositoryConfiguration getRepositoryConfiguration();

  public boolean supportsRepository();
}