package net.sf.anathema.framework.presenter.menu;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.initialization.Extension;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.resources.IResources;

@Extension(id="Menu.Extra")
public class MenuExtensionPoint implements IAnathemaExtension, IMenuExtensionPoint {

  private final List<IMenuItem> menuItems = new ArrayList<IMenuItem>();

  public void initialize(IResources resources, IDataFileProvider dataFileProvider, Instantiater instantiater) {
    // nothing to do
  }

  public IMenuItem[] getMenuItems() {
    return menuItems.toArray(new IMenuItem[menuItems.size()]);
  }

  public void addMenuItem(IMenuItem item) {
    menuItems.add(item);
  }
}