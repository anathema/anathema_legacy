package net.sf.anathema.framework.module;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.presenter.IItemViewFactory;
import net.sf.anathema.framework.presenter.IWizardFactory;
import net.sf.anathema.framework.presenter.item.DefaultItemTypeViewProperties;
import net.sf.anathema.framework.presenter.item.ItemTypeViewPropertiesExtensionPoint;
import net.sf.anathema.framework.presenter.menu.IMenuExtensionPoint;
import net.sf.anathema.framework.presenter.menu.IMenuItem;
import net.sf.anathema.framework.presenter.menu.MenuExtensionPoint;
import net.sf.anathema.framework.repository.filechooser.RepositoryFileChooserPropertiesExtensionPoint;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractItemTypeConfiguration {

  private final IItemType type;

  public AbstractItemTypeConfiguration(IItemType type) {
    this.type = type;
  }

  public final void fillPresentationExtensionPoints(
      IRegistry<String, IAnathemaExtension> extensionPointRegistry,
      IResources resources,
      IAnathemaModel model,
      IAnathemaView view) {
    ItemTypeViewPropertiesExtensionPoint itemExtensionPoint = (ItemTypeViewPropertiesExtensionPoint) extensionPointRegistry.get(ItemTypeViewPropertiesExtensionPoint.ID);
    itemExtensionPoint.register(type, new DefaultItemTypeViewProperties(resources, getPrintNameKey()));
    if (isPersistable()) {
      registerRepositoryFileChooserProperties(extensionPointRegistry, resources);
    }
    else {
      IMenuItem[] addMenuItems = createAddMenuEntries(view, model, resources);
      MenuExtensionPoint extraExtensionPoint = (MenuExtensionPoint) extensionPointRegistry.get(IMenuExtensionPoint.EXTRA_MENU_EXTENSION_POINT_ID);
      addToMenuExtensionPoint(addMenuItems, extraExtensionPoint);
    }
  }

  private void addToMenuExtensionPoint(IMenuItem[] menuItems, MenuExtensionPoint extensionPoint) {
    for (IMenuItem item : menuItems) {
      extensionPoint.addMenuItem(item);
    }
  }

  private void registerRepositoryFileChooserProperties(
      IRegistry<String, IAnathemaExtension> extensionPointRegistry,
      IResources resources) {
    RepositoryFileChooserPropertiesExtensionPoint repositoryExtensionPoint = (RepositoryFileChooserPropertiesExtensionPoint) extensionPointRegistry.get(RepositoryFileChooserPropertiesExtensionPoint.ID);
    repositoryExtensionPoint.register(type, new DefaultObjectSelectionProperties(
        resources,
        getLoadMessageKey(),
        getLoadTitleKey()));
  }

  protected abstract String getPrintNameKey();

  protected abstract String getLoadTitleKey();

  protected abstract String getLoadMessageKey();

  public final void initModel(IAnathemaModel model) {
    if (isPersistable()) {
      model.getPersisterRegistry().register(type, createPersister(model));
    }
  }

  protected abstract IRepositoryItemPersister createPersister(IAnathemaModel model);

  public final void registerViewFactory(IAnathemaModel anathemaModel, IResources resources) {
    IRegistry<IItemType, IItemViewFactory> viewFactoryRegistry = anathemaModel.getViewFactoryRegistry();
    viewFactoryRegistry.register(type, createItemViewFactory(anathemaModel, resources));
  }

  protected abstract IItemViewFactory createItemViewFactory(IAnathemaModel anathemaModel, IResources resources);

  protected boolean isPersistable() {
    return true;
  }

  public final IItemType getItemType() {
    return type;
  }

  protected abstract IMenuItem[] createAddMenuEntries(
      IAnathemaView view,
      IAnathemaModel anathemaModel,
      IResources resources);

  public void registerCreationWizardFactory(IAnathemaModel anathemaModel, IResources resources) {
    IRegistry<IItemType, IWizardFactory> viewFactoryRegistry = anathemaModel.getCreationWizardFactoryRegistry();
    viewFactoryRegistry.register(type, createCreationWizardPageFactory(anathemaModel, resources));
  }

  protected abstract IWizardFactory createCreationWizardPageFactory(IAnathemaModel anathemaModel, IResources resources);
}