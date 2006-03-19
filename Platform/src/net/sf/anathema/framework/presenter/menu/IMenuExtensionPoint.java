package net.sf.anathema.framework.presenter.menu;

public interface IMenuExtensionPoint {
  public static final String NEW_MENU_EXTENSION_POINT_ID = "New" + MenuExtensionPoint.class.getName(); //$NON-NLS-1$
  public static final String EXTRA_MENU_EXTENSION_POINT_ID = "Extra" + MenuExtensionPoint.class.getName(); //$NON-NLS-1$
  public static final String HELP_MENU_EXTENSION_POINT_ID = "Help" + MenuExtensionPoint.class.getName(); //$NON-NLS-1$
  
  public IMenuItem[] getMenuItems();

  public void addMenuItem(IMenuItem item);
}