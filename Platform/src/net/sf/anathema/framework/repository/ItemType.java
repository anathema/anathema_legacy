package net.sf.anathema.framework.repository;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.IRepositoryConfiguration;
import net.sf.anathema.lib.util.IIdentificate;

public class ItemType implements IItemType, IIdentificate {

  private final String id;
  private final IRepositoryConfiguration repositoryConfiguration;
  private final boolean integrated;

  public ItemType(String id, IRepositoryConfiguration configuration) {
    this(id, configuration, configuration != null);
  }

  public ItemType(String id, IRepositoryConfiguration configuration, boolean integrated) {
    this.id = id;
    this.repositoryConfiguration = configuration;
    this.integrated = integrated;
  }

  @Override
  public final String getId() {
    return id;
  }

  @Override
  public String toString() {
    return id;
  }


  @Override
  public boolean supportsRepository() {
    return repositoryConfiguration != null;
  }

  @Override
  public boolean isIntegrated() {
    return integrated;
  }

  @Override
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
