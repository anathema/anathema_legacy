package net.sf.anathema.framework.module;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.menu.IMenuExtensionPoint;
import net.sf.anathema.framework.presenter.menu.IMenuItem;
import net.sf.anathema.framework.presenter.menu.MenuExtensionPoint;
import net.sf.anathema.framework.view.ApplicationView;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractNonPersistableItemTypeConfiguration extends AbstractItemTypeConfiguration {

  public AbstractNonPersistableItemTypeConfiguration(IItemType type) {
    super(type);
  }

  @Override
  public final void fillPresentationExtensionPoints(
      IRegistry<String, IAnathemaExtension> extensionPointRegistry,
      IResources resources,
      IAnathemaModel model,
      ApplicationView view) {
    IMenuItem[] addMenuItems = createAddMenuEntries(view, model, resources);
    MenuExtensionPoint extraExtensionPoint = (MenuExtensionPoint) extensionPointRegistry.get(IMenuExtensionPoint.EXTRA_MENU_EXTENSION_POINT_ID);
    for (IMenuItem item : addMenuItems) {
      extraExtensionPoint.addMenuItem(item);
    }
  }

  protected abstract IMenuItem[] createAddMenuEntries(
      ApplicationView view,
      IAnathemaModel anathemaModel,
      IResources resources);

  @Override
  public void initModel(IAnathemaModel model) {
    //nothing to do    
  }
}