package net.sf.anathema.framework.presenter.menu;

public interface IMenuExtensionPoint {
  public static final String NEW_MENU_EXTENSION_POINT_ID = "Menu.New"; //$NON-NLS-1$
  public static final String EXTRA_MENU_EXTENSION_POINT_ID = "Menu.Extra"; //$NON-NLS-1$
  
  public IMenuItem[] getMenuItems();

  public void addMenuItem(IMenuItem item);
}