package net.sf.anathema.framework.repository;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.IRepositoryConfiguration;
import net.sf.anathema.lib.util.IIdentificate;

public class ItemType implements IItemType, IIdentificate {

  private final String id;
  private IRepositoryConfiguration repositoryConfiguration;

  public ItemType(String id, IRepositoryConfiguration configuration) {
    this.id = id;
    this.repositoryConfiguration = configuration;
  }

  @Override
  public final String getId() {
    return id;
  }

  @Override
  public String toString() {
    return id;
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