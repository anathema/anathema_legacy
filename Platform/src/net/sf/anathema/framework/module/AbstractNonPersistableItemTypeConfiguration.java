package net.sf.anathema.framework.module;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;

public abstract class AbstractNonPersistableItemTypeConfiguration extends AbstractItemTypeConfiguration {

  public AbstractNonPersistableItemTypeConfiguration(IItemType type) {
    super(type);
  }

  @Override
  protected final String getLoadTitleKey() {
    throw new UnsupportedOperationException("Non persistable item must not be loaded"); //$NON-NLS-1$
  }

  @Override
  protected final String getLoadMessageKey() {
    throw new UnsupportedOperationException("Non persistable item must not be loaded"); //$NON-NLS-1$
  }

  @Override
  protected final IRepositoryItemPersister createPersister(IAnathemaModel model) {
    throw new UnsupportedOperationException("Non persistable item must not be loaded"); //$NON-NLS-1$
  }

  @Override
  protected final boolean isPersistable() {
    return false;
  }
}