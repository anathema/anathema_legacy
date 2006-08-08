package net.sf.anathema.framework.module;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.model.AnathemaModel;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;

public abstract class AbstractPersistableItemTypeConfiguration extends AbstractItemTypeConfiguration {

  public AbstractPersistableItemTypeConfiguration(IItemType type) {
    super(type);
  }

  @Override
  public void initModel(AnathemaModel model) {
    model.getPersisterRegistry().register(getItemType(), createPersister(model));
  }

  protected abstract String getLoadMessageKey();

  protected abstract String getLoadTitleKey();

  protected abstract IRepositoryItemPersister createPersister(IAnathemaModel model);
}