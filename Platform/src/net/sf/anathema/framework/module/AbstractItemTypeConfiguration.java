package net.sf.anathema.framework.module;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.model.AnathemaModel;
import net.sf.anathema.framework.presenter.IItemViewFactory;
import net.sf.anathema.framework.presenter.IWizardFactory;
import net.sf.anathema.framework.presenter.item.DefaultItemTypeViewProperties;
import net.sf.anathema.framework.presenter.item.ItemTypeViewPropertiesExtensionPoint;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractItemTypeConfiguration {

  private final IItemType type;

  public AbstractItemTypeConfiguration(IItemType type) {
    this.type = type;
  }

  public void fillPresentationExtensionPoints(
      IRegistry<String, IAnathemaExtension> extensionPointRegistry,
      IResources resources,
      IAnathemaModel model,
      IAnathemaView view) {
    ItemTypeViewPropertiesExtensionPoint itemExtensionPoint = (ItemTypeViewPropertiesExtensionPoint) extensionPointRegistry.get(ItemTypeViewPropertiesExtensionPoint.ID);
    itemExtensionPoint.register(type, new DefaultItemTypeViewProperties(resources, getPrintNameKey()));
  }

  public final void registerViewFactory(IAnathemaModel anathemaModel, IResources resources) {
    IRegistry<IItemType, IItemViewFactory> viewFactoryRegistry = anathemaModel.getViewFactoryRegistry();
    viewFactoryRegistry.register(type, createItemViewFactory(anathemaModel, resources));
  }

  public final IItemType getItemType() {
    return type;
  }

  public void registerCreationWizardFactory(IAnathemaModel anathemaModel, IResources resources) {
    IRegistry<IItemType, IWizardFactory> viewFactoryRegistry = anathemaModel.getCreationWizardFactoryRegistry();
    viewFactoryRegistry.register(type, createCreationWizardPageFactory(anathemaModel, resources));
  }

  protected abstract String getPrintNameKey();

  protected abstract IItemViewFactory createItemViewFactory(IAnathemaModel anathemaModel, IResources resources);

  protected abstract IWizardFactory createCreationWizardPageFactory(IAnathemaModel anathemaModel, IResources resources);

  public abstract void initModel(AnathemaModel model);
}