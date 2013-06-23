package net.sf.anathema.framework.module;

import net.sf.anathema.framework.item.IItemType;

public abstract class AbstractNonPersistableItemTypeConfiguration implements ItemTypeConfiguration {

  private IItemType type;

  public AbstractNonPersistableItemTypeConfiguration(IItemType type) {
    this.type = type;
  }

  @Override
  public IItemType getItemType() {
    return type;
  }
}