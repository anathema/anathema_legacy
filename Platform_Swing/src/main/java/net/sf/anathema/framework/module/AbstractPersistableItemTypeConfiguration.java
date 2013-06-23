package net.sf.anathema.framework.module;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.persistence.RepositoryItemPersister;
import net.sf.anathema.framework.presenter.item.ItemTypeCreationViewPropertiesExtensionPoint;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.framework.view.ApplicationView;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.Resources;

public abstract class AbstractPersistableItemTypeConfiguration extends AbstractItemTypeConfiguration {

  public AbstractPersistableItemTypeConfiguration(IItemType type) {
    super(type);
  }

  @Override
  public void fillPresentationExtensionPoints(IRegistry<String, IAnathemaExtension> extensionPointRegistry, Resources resources,
                                              IApplicationModel model, ApplicationView view) {
    ItemTypeCreationViewPropertiesExtensionPoint itemCreationExtensionPoint =
            (ItemTypeCreationViewPropertiesExtensionPoint) extensionPointRegistry.get(ItemTypeCreationViewPropertiesExtensionPoint.ID);
    itemCreationExtensionPoint.register(getItemType(), createItemTypeCreationProperties(model, resources));
  }

  @Override
  public void initModel(IApplicationModel model) {
    model.getPersisterRegistry().register(getItemType(), createPersister(model));
  }

  protected abstract IItemTypeViewProperties createItemTypeCreationProperties(IApplicationModel anathemaModel, Resources resources);

  protected abstract RepositoryItemPersister createPersister(IApplicationModel model);
}