package net.sf.anathema.framework.module;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.presenter.item.ItemTypeCreationViewPropertiesExtensionPoint;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractPersistableItemTypeConfiguration extends AbstractItemTypeConfiguration {

  public AbstractPersistableItemTypeConfiguration(IItemType type) {
    super(type);
  }

  public void fillPresentationExtensionPoints(
      IRegistry<String, IAnathemaExtension> extensionPointRegistry,
      IResources resources,
      IAnathemaModel model,
      IAnathemaView view) {
    ItemTypeCreationViewPropertiesExtensionPoint itemCreationExtensionPoint = (ItemTypeCreationViewPropertiesExtensionPoint) extensionPointRegistry.get(ItemTypeCreationViewPropertiesExtensionPoint.ID);
    itemCreationExtensionPoint.register(getItemType(), createItemTypeCreationProperties(model, resources));
  }

  public void initModel(IAnathemaModel model) {
    model.getPersisterRegistry().register(getItemType(), createPersister(model));
  }

  protected abstract IItemTypeViewProperties createItemTypeCreationProperties(
      IAnathemaModel anathemaModel,
      IResources resources);

  protected abstract IRepositoryItemPersister createPersister(IAnathemaModel model);
}