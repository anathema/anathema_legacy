package net.sf.anathema.framework.module;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.presenter.action.AnathemaExitAction;
import net.sf.anathema.framework.presenter.action.menu.help.updatecheck.UpdateAction;
import net.sf.anathema.framework.presenter.action.preferences.AnathemaAboutAction;
import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.framework.presenter.action.preferences.ShowPreferencesAction;
import net.sf.anathema.framework.repository.tree.RepositoryViewAction;
import net.sf.anathema.framework.view.MenuBar;
import net.sf.anathema.framework.view.menu.IMenu;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.Action;
import javax.swing.KeyStroke;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class AnathemaCoreMenu {

  public void add(Resources resources, IApplicationModel model, MenuBar menubar) {
    IMenu mainMenu = menubar.getMainMenu();
    mainMenu.addMenuItem(createExportImportAction(resources, model));
    mainMenu.addSeparator();
    mainMenu.addMenuItem(ShowPreferencesAction.createMenuAction(resources, createSystemPreferences(model)));
    mainMenu.addSeparator();
    mainMenu.addMenuItem(AnathemaExitAction.createMenuAction(resources));
    IMenu helpMenu = menubar.getHelpMenu();
    helpMenu.addMenuItem(UpdateAction.createMenuAction(resources));
    helpMenu.addMenuItem(AnathemaAboutAction.createMenuAction(resources));
  }

  private static Action createExportImportAction(Resources resources, IApplicationModel model) {
    Action action = RepositoryViewAction.createMenuAction(resources, model);
    if (action instanceof SmartAction) {
      SmartAction smartAction = (SmartAction) action;
      smartAction.setName(resources.getString("AnathemaCore.Tools.ExportImport.Name"));
      smartAction.setAcceleratorKey(KeyStroke.getKeyStroke(KeyEvent.VK_E, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    }
    return action;
  }

  private IPreferencesElement[] createSystemPreferences(IApplicationModel anathemaModel) {
    IRegistry<String, IAnathemaExtension> registry = anathemaModel.getExtensionPointRegistry();
    IAnathemaExtension extension = registry.get(PreferencesElementsExtensionPoint.ID);
    return ((PreferencesElementsExtensionPoint) extension).getAllPreferencesElements();
  }
}