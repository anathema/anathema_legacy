package net.sf.anathema.framework.module;

import javax.swing.JMenu;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.model.ItemManagmentModelListener;
import net.sf.anathema.framework.presenter.IItemMangementModel;
import net.sf.anathema.framework.presenter.IModelViewMapping;
import net.sf.anathema.framework.presenter.ModelViewMapping;
import net.sf.anathema.framework.presenter.action.AnathemaAboutAction;
import net.sf.anathema.framework.presenter.action.AnathemaExitAction;
import net.sf.anathema.framework.presenter.action.ItemTypeLoadAction;
import net.sf.anathema.framework.presenter.action.preferences.AnathemaPreferencesAction;
import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.framework.presenter.itemmanagement.AnathemaCloseAction;
import net.sf.anathema.framework.presenter.itemmanagement.AnathemaSaveAction;
import net.sf.anathema.framework.presenter.itemmanagement.AnathemaSaveAllAction;
import net.sf.anathema.framework.presenter.itemmanagement.ItemViewSelectionListener;
import net.sf.anathema.framework.presenter.menu.IMenuExtensionPoint;
import net.sf.anathema.framework.presenter.menu.IMenuItem;
import net.sf.anathema.framework.presenter.menu.MenuExtensionPoint;
import net.sf.anathema.framework.reporting.controller.AnathemaPrintAction;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.framework.view.IMenuBar;
import net.sf.anathema.framework.view.menu.IMenu;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public class AnathemaCoreModule implements IAnathemaModule {

  public void initPresentation(IResources resources, IAnathemaModel model, IAnathemaView view) {
    initMenu(resources, model, view);
    initModelViewMapping(resources, model, view);
  }

  private void initModelViewMapping(IResources resources, IAnathemaModel model, IAnathemaView view) {
    IModelViewMapping mapping = new ModelViewMapping();
    IItemMangementModel itemManagement = model.getItemManagement();
    itemManagement.addListener(new ItemManagmentModelListener(
        model.getViewFactoryRegistry(),
        view,
        mapping,
        new ItemActionFactory(itemManagement, resources)));
    view.addViewSelectionListener(new ItemViewSelectionListener(itemManagement, mapping));
  }

  private void initMenu(IResources resources, IAnathemaModel model, IAnathemaView anathemaView) {
    IMenuBar menuBar = anathemaView.getMenuBar();
    IMenu mainMenu = menuBar.getMainMenu();
    mainMenu.addMenuItem(createNewMenu(model, resources));
    mainMenu.addMenuItem(createLoadMenu(model, resources));
    mainMenu.addMenuItem(AnathemaCloseAction.createMenuAction(model.getItemManagement(), resources));
    mainMenu.addSeparator();
    mainMenu.addMenuItem(AnathemaSaveAction.createMenuAction(model, resources));
    mainMenu.addMenuItem(AnathemaSaveAllAction.createMenuAction(model, resources));
    mainMenu.addSeparator();
    mainMenu.addMenuItem(AnathemaPrintAction.createMenuAction(model, resources));
    mainMenu.addSeparator();
    mainMenu.addMenuItem(AnathemaExitAction.createMenuAction(resources));
    menuBar.getHelpMenu().addMenuItem(new AnathemaAboutAction(resources));
    menuBar.addMenu(createExtraMenu(model, resources));
  }

  private JMenu createNewMenu(IAnathemaModel anathemaModel, IResources resources) {
    String menuName = resources.getString("AnathemaCore.Tools.New.Name"); //$NON-NLS-1$
    return createMenuFromExtensionPoint(anathemaModel, menuName, IMenuExtensionPoint.NEW_MENU_EXTENSION_POINT_ID);
  }

  private JMenu createExtraMenu(IAnathemaModel anathemaModel, IResources resources) {
    String menuName = resources.getString("AnathemaCore.Tools.Extra.Name"); //$NON-NLS-1$
    JMenu extraMenu = createMenuFromExtensionPoint(
        anathemaModel,
        menuName,
        IMenuExtensionPoint.EXTRA_MENU_EXTENSION_POINT_ID);
    extraMenu.setMnemonic('E');
    extraMenu.add(AnathemaPreferencesAction.createMenuAction(resources, createSystemPreferences(anathemaModel)));
    return extraMenu;
  }

  private IPreferencesElement[] createSystemPreferences(IAnathemaModel anathemaModel) {
    PreferencesElementsExtensionPoint preferencesPoint = (PreferencesElementsExtensionPoint) anathemaModel.getExtensionPointRegistry()
        .get(PreferencesElementsExtensionPoint.ID);
    return preferencesPoint.getAllPreferencesElements();
  }

  private JMenu createMenuFromExtensionPoint(IAnathemaModel anathemaModel, String menuName, String extensionPointId) {
    JMenu menu = new JMenu(menuName);
    IRegistry<String, IAnathemaExtension> extensionPointRegistry = anathemaModel.getExtensionPointRegistry();
    MenuExtensionPoint newExtensionPoint = (MenuExtensionPoint) extensionPointRegistry.get(extensionPointId);
    for (IMenuItem item : newExtensionPoint.getMenuItems()) {
      item.addToMenu(menu);
    }
    return menu;
  }

  private JMenu createLoadMenu(IAnathemaModel anathemaModel, IResources resources) {
    JMenu menu = new JMenu(resources.getString("AnathemaPersistence.LoadMenu.Name")); //$NON-NLS-1$
    for (IItemType itemType : anathemaModel.getPersisterRegistry().getIds(new IItemType[0])) {
      menu.add(ItemTypeLoadAction.createMenuAction(anathemaModel, itemType, resources));
    }
    return menu;
  }
}