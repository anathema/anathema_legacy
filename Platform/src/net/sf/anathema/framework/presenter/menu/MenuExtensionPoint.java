package net.sf.anathema.framework.presenter.menu;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.resources.IResources;

public class MenuExtensionPoint implements IAnathemaExtension, IMenuExtensionPoint {

  private final List<IMenuItem> menuItems = new ArrayList<IMenuItem>();

  public void initialize(IResources resources, IDataFileProvider dataFileProvider) {
    // nothing to do
  }

  public IMenuItem[] getMenuItems() {
    return menuItems.toArray(new IMenuItem[0]);
  }

  public void addMenuItem(IMenuItem item) {
    menuItems.add(item);
  }
}