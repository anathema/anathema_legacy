package net.sf.anathema.framework.repository;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.RepositoryConfiguration;
import net.sf.anathema.lib.util.Identifier;

public class ItemType implements IItemType, Identifier {

  private final String id;
  private final RepositoryConfiguration repositoryConfiguration;
  private final boolean integrated;

  public ItemType(String id, RepositoryConfiguration configuration) {
    this(id, configuration, true);
  }

  public ItemType(String id, RepositoryConfiguration configuration, boolean integrated) {
    this.id = id;
    this.repositoryConfiguration = configuration;
    this.integrated = integrated;
  }

  @Override
  public final String getId() {
    return id;
  }

  @Override
  public boolean isIntegrated() {
    return integrated;
  }

  @Override
  public RepositoryConfiguration getRepositoryConfiguration() {
    return repositoryConfiguration;
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof ItemType && ((ItemType) obj).repositoryConfiguration.equals(repositoryConfiguration);
  }

  @Override
  public int hashCode() {
    return repositoryConfiguration.hashCode();
  }
}