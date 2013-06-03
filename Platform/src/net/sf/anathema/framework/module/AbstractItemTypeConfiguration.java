package net.sf.anathema.framework.module;

import net.sf.anathema.framework.item.IItemType;

public abstract class AbstractItemTypeConfiguration implements IItemTypeConfiguration {

  private final IItemType type;

  public AbstractItemTypeConfiguration(IItemType type) {
    this.type = type;
  }

  @Override
  public final IItemType getItemType() {
    return type;
  }
}