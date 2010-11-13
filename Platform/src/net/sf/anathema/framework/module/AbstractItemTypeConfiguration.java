package net.sf.anathema.framework.module;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.IItemViewFactory;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractItemTypeConfiguration implements IItemTypeConfiguration {

  private final IItemType type;

  public AbstractItemTypeConfiguration(IItemType type) {
    this.type = type;
  }

  public final void registerViewFactory(IAnathemaModel anathemaModel, IResources resources) {
    IRegistry<IItemType, IItemViewFactory> viewFactoryRegistry = anathemaModel.getViewFactoryRegistry();
    viewFactoryRegistry.register(type, createItemViewFactory(anathemaModel, resources));
  }

  public final IItemType getItemType() {
    return type;
  }

  protected abstract IItemViewFactory createItemViewFactory(IAnathemaModel anathemaModel, IResources resources);
}