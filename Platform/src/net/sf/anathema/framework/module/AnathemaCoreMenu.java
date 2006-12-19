package net.sf.anathema.framework.module;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.presenter.action.AnathemaAboutAction;
import net.sf.anathema.framework.presenter.action.AnathemaExitAction;
import net.sf.anathema.framework.presenter.action.AnathemaLoadAction;
import net.sf.anathema.framework.presenter.action.AnathemaNewAction;
import net.sf.anathema.framework.presenter.action.preferences.AnathemaPreferencesAction;
import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.framework.presenter.itemmanagement.AnathemaCloseAction;
import net.sf.anathema.framework.presenter.itemmanagement.AnathemaSaveAction;
import net.sf.anathema.framework.presenter.itemmanagement.AnathemaSaveAllAction;
import net.sf.anathema.framework.presenter.menu.IAnathemaMenu;
import net.sf.anathema.framework.presenter.menu.IMenuExtensionPoint;
import net.sf.anathema.framework.presenter.menu.IMenuItem;
import net.sf.anathema.framework.presenter.menu.MenuExtensionPoint;
import net.sf.anathema.framework.reporting.AnathemaPrintAction;
import net.sf.anathema.framework.repository.tree.RepositoryViewAction;
import net.sf.anathema.framework.view.IMenuBar;
import net.sf.anathema.framework.view.menu.IMenu;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public class AnathemaCoreMenu implements IAnathemaMenu {

  public void add(IResources resources, IAnathemaModel model, IMenuBar menubar) {
    IMenu mainMenu = menubar.getMainMenu();
    mainMenu.addMenuItem(AnathemaNewAction.createMenuAction(model, resources));
    mainMenu.addMenuItem(AnathemaLoadAction.createMenuAction(model, resources));
    mainMenu.addMenuItem(AnathemaCloseAction.createMenuAction(model.getItemManagement(), resources));
    mainMenu.addSeparator();
    mainMenu.addMenuItem(AnathemaSaveAction.createMenuAction(model, resources));
    mainMenu.addMenuItem(AnathemaSaveAllAction.createMenuAction(model, resources));
    mainMenu.addSeparator();
    mainMenu.addMenuItem(AnathemaPrintAction.createMenuAction(model, resources));
    mainMenu.addSeparator();
    mainMenu.addMenuItem(AnathemaExitAction.createMenuAction(resources));
    IMenu helpMenu = menubar.getHelpMenu();
    helpMenu.addMenuItem(AnathemaAboutAction.createMenuAction(resources));
    createExtraMenu(model, resources, menubar);
  }

  private void createExtraMenu(IAnathemaModel anathemaModel, IResources resources, IMenuBar menubar) {
    IMenu menu = menubar.addMenu(resources.getString("AnathemaCore.Tools.Extra.Name")); //$NON-NLS-1$
    createMenuFromExtensionPoint(anathemaModel, menu);
    menu.setMnemonic('E');
    menu.addMenuItem(RepositoryViewAction.createMenuAction(resources, anathemaModel));
    menu.addMenuItem(AnathemaPreferencesAction.createMenuAction(resources, createSystemPreferences(anathemaModel)));
  }

  private void createMenuFromExtensionPoint(IAnathemaModel anathemaModel, IMenu menu) {
    IRegistry<String, IAnathemaExtension> extensionPointRegistry = anathemaModel.getExtensionPointRegistry();
    MenuExtensionPoint newExtensionPoint = (MenuExtensionPoint) extensionPointRegistry.get(IMenuExtensionPoint.EXTRA_MENU_EXTENSION_POINT_ID);
    for (IMenuItem item : newExtensionPoint.getMenuItems()) {
      item.addToMenu(menu);
    }
  }

  private IPreferencesElement[] createSystemPreferences(IAnathemaModel anathemaModel) {
    PreferencesElementsExtensionPoint preferencesPoint = (PreferencesElementsExtensionPoint) anathemaModel.getExtensionPointRegistry()
        .get(PreferencesElementsExtensionPoint.ID);
    return preferencesPoint.getAllPreferencesElements();
  }
}