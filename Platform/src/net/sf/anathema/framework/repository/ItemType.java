package net.sf.anathema.framework.repository;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.IRepositoryConfiguration;
import net.sf.anathema.lib.util.Identificate;

public class ItemType extends Identificate implements IItemType {

  private IRepositoryConfiguration repositoryConfiguration;

  public ItemType(String id, IRepositoryConfiguration configuration) {
    super(id);
    this.repositoryConfiguration = configuration;
  }

  public boolean supportsRepository() {
    return repositoryConfiguration != null;
  }

  public IRepositoryConfiguration getRepositoryConfiguration() {
    return repositoryConfiguration;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ItemType)) {
      return false;
    }
    return super.equals(obj) && ((ItemType) obj).repositoryConfiguration.equals(repositoryConfiguration);
  }

  @Override
  public int hashCode() {
    return super.hashCode() + (repositoryConfiguration != null ? repositoryConfiguration.hashCode() : 0);
  }
}