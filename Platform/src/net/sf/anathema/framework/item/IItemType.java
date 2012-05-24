package net.sf.anathema.framework.item;

import net.sf.anathema.lib.util.Identified;

public interface IItemType extends Identified {

  IRepositoryConfiguration getRepositoryConfiguration();

  boolean supportsRepository();

  boolean isIntegrated();
}
