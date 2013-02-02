package net.sf.anathema.framework.module;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.presenter.action.AnathemaExitAction;
import net.sf.anathema.framework.presenter.action.AnathemaLoadAction;
import net.sf.anathema.framework.presenter.action.AnathemaNewAction;
import net.sf.anathema.framework.presenter.action.menu.help.AnathemaAboutAction;
import net.sf.anathema.framework.presenter.action.menu.help.updatecheck.UpdateAction;
import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.framework.presenter.action.preferences.ShowPreferencesAction;
import net.sf.anathema.framework.presenter.itemmanagement.AnathemaSaveAction;
import net.sf.anathema.framework.presenter.itemmanagement.AnathemaSaveAllAction;
import net.sf.anathema.framework.presenter.itemmanagement.SelectedItemCloseAction;
import net.sf.anathema.framework.presenter.menu.IAnathemaMenu;
import net.sf.anathema.framework.presenter.menu.IMenuExtensionPoint;
import net.sf.anathema.framework.presenter.menu.IMenuItem;
import net.sf.anathema.framework.presenter.menu.MenuExtensionPoint;
import net.sf.anathema.framework.reporting.AbstractPrintAction;
import net.sf.anathema.framework.reporting.ControlledPrintAction;
import net.sf.anathema.framework.reporting.QuickPrintAction;
import net.sf.anathema.framework.repository.tree.RepositoryViewAction;
import net.sf.anathema.framework.view.MenuBar;
import net.sf.anathema.framework.view.menu.IMenu;
import net.sf.anathema.initialization.Menu;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Action;
import javax.swing.KeyStroke;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

@Menu
@Weight(weight = 10)
public class AnathemaCoreMenu implements IAnathemaMenu {

  @Override
  public void add(IResources resources, IAnathemaModel model, MenuBar menubar) {
    IMenu mainMenu = menubar.getMainMenu();
    mainMenu.addMenuItem(AnathemaNewAction.createMenuAction(model, resources));
    mainMenu.addMenuItem(AnathemaLoadAction.createMenuAction(model, resources));
    mainMenu.addMenuItem(SelectedItemCloseAction.createMenuAction(model.getItemManagement(), resources));
    mainMenu.addSeparator();
    mainMenu.addMenuItem(AnathemaSaveAction.createMenuAction(model, resources));
    mainMenu.addMenuItem(AnathemaSaveAllAction.createMenuAction(model, resources));
    mainMenu.addSeparator();
    mainMenu.addMenuItem(createExportImportAction(resources, model));
    mainMenu.addSeparator();
    mainMenu.addMenuItem(ControlledPrintAction.createMenuAction(model, resources));
    if (AbstractPrintAction.isAutoOpenSupported()) {
      mainMenu.addMenuItem(QuickPrintAction.createMenuAction(model, resources));
    }
    mainMenu.addSeparator();
    mainMenu.addMenuItem(AnathemaExitAction.createMenuAction(resources));
    IMenu helpMenu = menubar.getHelpMenu();
    helpMenu.addMenuItem(UpdateAction.createMenuAction(resources));
    helpMenu.addMenuItem(AnathemaAboutAction.createMenuAction(resources));
    createExtraMenu(model, resources, menubar);
  }

  private static Action createExportImportAction(IResources resources, IAnathemaModel model) {
    Action action = RepositoryViewAction.createMenuAction(resources, model);
    if (action instanceof SmartAction) {
      SmartAction smartAction = (SmartAction) action;
      smartAction.setName(resources.getString("AnathemaCore.Tools.ExportImport.Name"));
      smartAction.setAcceleratorKey(
              KeyStroke.getKeyStroke(KeyEvent.VK_E, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    }
    return action;
  }

  private void createExtraMenu(IAnathemaModel anathemaModel, IResources resources, MenuBar menubar) {
    IMenu menu = menubar.addMenu(resources.getString("AnathemaCore.Tools.Extra.Name")); //$NON-NLS-1$
    createMenuFromExtensionPoint(anathemaModel, menu);
    menu.setMnemonic('E');
    menu.addMenuItem(RepositoryViewAction.createMenuAction(resources, anathemaModel));
    menu.addMenuItem(ShowPreferencesAction.createMenuAction(resources, createSystemPreferences(anathemaModel)));
  }

  private void createMenuFromExtensionPoint(IAnathemaModel anathemaModel, IMenu menu) {
    IRegistry<String, IAnathemaExtension> extensionPointRegistry = anathemaModel.getExtensionPointRegistry();
    MenuExtensionPoint newExtensionPoint = (MenuExtensionPoint) extensionPointRegistry.get(
            IMenuExtensionPoint.EXTRA_MENU_EXTENSION_POINT_ID);
    for (IMenuItem item : newExtensionPoint.getMenuItems()) {
      item.addToMenu(menu);
    }
  }

  private IPreferencesElement[] createSystemPreferences(IAnathemaModel anathemaModel) {
    PreferencesElementsExtensionPoint preferencesPoint = (PreferencesElementsExtensionPoint) anathemaModel.getExtensionPointRegistry().get(
            PreferencesElementsExtensionPoint.ID);
    return preferencesPoint.getAllPreferencesElements();
  }
}
