package net.sf.anathema.framework.presenter.menu;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.framework.extension.IExtensionPoint;

public class MenuExtensionPoint implements IExtensionPoint, IMenuExtensionPoint {

  private final List<IMenuItem> menuItems = new ArrayList<IMenuItem>();

  public IMenuItem[] getMenuItems() {
    return menuItems.toArray(new IMenuItem[0]);
  }

  public void addMenuItem(IMenuItem item) {
    menuItems.add(item);
  }
}