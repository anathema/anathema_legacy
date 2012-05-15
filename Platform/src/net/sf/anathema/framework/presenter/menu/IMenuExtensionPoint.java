package net.sf.anathema.framework.presenter.menu;

public interface IMenuExtensionPoint {
  String EXTRA_MENU_EXTENSION_POINT_ID = "Menu.Extra"; //$NON-NLS-1$

  IMenuItem[] getMenuItems();

  void addMenuItem(IMenuItem item);
}