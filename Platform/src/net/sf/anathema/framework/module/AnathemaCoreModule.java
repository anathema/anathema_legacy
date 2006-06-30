package net.sf.anathema.framework.module;

import javax.swing.JMenu;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.model.ItemManagmentModelListener;
import net.sf.anathema.framework.module.preferences.LanguagePreferencesElement;
import net.sf.anathema.framework.module.preferences.LookAndFeelPreferencesElement;
import net.sf.anathema.framework.module.preferences.MaximizePreferencesElement;
import net.sf.anathema.framework.module.preferences.OpenPdfPreferencesElement;
import net.sf.anathema.framework.module.preferences.RepositoryPreferencesElement;
import net.sf.anathema.framework.module.preferences.ToolTipTimePreferencesElement;
import net.sf.anathema.framework.presenter.IItemMangementModel;
import net.sf.anathema.framework.presenter.IModelViewMapping;
import net.sf.anathema.framework.presenter.ModelViewMapping;
import net.sf.anathema.framework.presenter.action.AnathemaAboutAction;
import net.sf.anathema.framework.presenter.action.AnathemaExitAction;
import net.sf.anathema.framework.presenter.action.ItemTypeLoadAction;
import net.sf.anathema.framework.presenter.action.preferences.AnathemaPreferencesAction;
import net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants;
import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.framework.presenter.item.ItemTypeViewPropertiesExtensionPoint;
import net.sf.anathema.framework.presenter.itemmanagement.AnathemaCloseAction;
import net.sf.anathema.framework.presenter.itemmanagement.AnathemaSaveAction;
import net.sf.anathema.framework.presenter.itemmanagement.AnathemaSaveAllAction;
import net.sf.anathema.framework.presenter.itemmanagement.ItemViewSelectionListener;
import net.sf.anathema.framework.presenter.menu.IMenuExtensionPoint;
import net.sf.anathema.framework.presenter.menu.IMenuItem;
import net.sf.anathema.framework.presenter.menu.MenuExtensionPoint;
import net.sf.anathema.framework.reporting.controller.AnathemaPrintAction;
import net.sf.anathema.framework.repository.filechooser.RepositoryFileChooserPropertiesExtensionPoint;
import net.sf.anathema.framework.view.IAnathemaMenu;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.framework.view.menu.IMenuBar;
import net.sf.anathema.lib.control.WindowsUtilities;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public class AnathemaCoreModule extends AbstractAnathemaModule {

  private IAnathemaView anathemaView;

  @Override
  public void initPresentationExtensionPoints(IRegistry<String, IAnathemaExtension> registry, IResources resources) {
    registry.register(
        RepositoryFileChooserPropertiesExtensionPoint.ID,
        new RepositoryFileChooserPropertiesExtensionPoint(resources));
    registry.register(ItemTypeViewPropertiesExtensionPoint.ID, new ItemTypeViewPropertiesExtensionPoint());
    registry.register(IMenuExtensionPoint.NEW_MENU_EXTENSION_POINT_ID, new MenuExtensionPoint());
    registry.register(IMenuExtensionPoint.EXTRA_MENU_EXTENSION_POINT_ID, new MenuExtensionPoint());
    registry.register(PreferencesElementsExtensionPoint.ID, new PreferencesElementsExtensionPoint());
  }

  @Override
  public void fillPresentationExtensionPoints(
      IRegistry<String, IAnathemaExtension> extensionPointRegistry,
      IAnathemaModel model,
      IResources resources,
      IAnathemaView view) {
    super.fillPresentationExtensionPoints(extensionPointRegistry, model, resources, view);
    fillPreferencesExtensionPoint(extensionPointRegistry);
  }

  private void fillPreferencesExtensionPoint(IRegistry<String, IAnathemaExtension> extensionPointRegistry) {
    PreferencesElementsExtensionPoint preferencesPoint = (PreferencesElementsExtensionPoint) extensionPointRegistry.get(PreferencesElementsExtensionPoint.ID);
    if (WindowsUtilities.isWindows()) {
      preferencesPoint.register(
          IAnathemaPreferencesConstants.LOOK_AND_FEEL_PREFERENCE,
          new LookAndFeelPreferencesElement());
    }
    preferencesPoint.register(IAnathemaPreferencesConstants.MAXIMIZE_PREFERENCE, new MaximizePreferencesElement());
    preferencesPoint.register(IAnathemaPreferencesConstants.OPEN_PDF_PREFERENCE, new OpenPdfPreferencesElement());
    preferencesPoint.register(IAnathemaPreferencesConstants.LOCALE_PREFERENCE, new LanguagePreferencesElement());
    preferencesPoint.register(
        IAnathemaPreferencesConstants.TOOL_TIP_TIME_PREFERENCE,
        new ToolTipTimePreferencesElement());
    preferencesPoint.register(IAnathemaPreferencesConstants.REPOSITORY_PREFERENCE, new RepositoryPreferencesElement());
  }

  public void initPresentation(IResources resources, IAnathemaModel model, IAnathemaView view) {
    this.anathemaView = view;
    IModelViewMapping mapping = new ModelViewMapping();
    initMenu(resources);
    initTools(resources);
    IItemMangementModel itemManagement = model.getItemManagement();
    itemManagement.addListener(new ItemManagmentModelListener(
        model.getViewFactoryRegistry(),
        view,
        mapping,
        new ItemActionFactory(itemManagement, resources)));
    view.addViewSelectionListener(new ItemViewSelectionListener(itemManagement, mapping));
  }

  private void initTools(IResources resources) {
    anathemaView.getToolbar().addTools(AnathemaSaveAction.createToolAction(getAnathemaModel(), resources));
    anathemaView.getToolbar().addTools(AnathemaSaveAllAction.createToolAction(getAnathemaModel(), resources));
    anathemaView.getToolbar().addTools(AnathemaPrintAction.createToolAction(getAnathemaModel(), resources));
  }

  private void initMenu(IResources resources) {
    IAnathemaMenu menuBar = anathemaView.getMenuBar();
    IMenuBar mainMenu = menuBar.getMainMenu();
    mainMenu.addMenuItem(createNewMenu(getAnathemaModel(), resources));
    mainMenu.addMenuItem(createLoadMenu(getAnathemaModel(), resources));
    mainMenu.addMenuItem(AnathemaCloseAction.createMenuAction(getAnathemaModel().getItemManagement(), resources));
    mainMenu.addSeparator();
    mainMenu.addMenuItem(AnathemaSaveAction.createMenuAction(getAnathemaModel(), resources));
    mainMenu.addMenuItem(AnathemaSaveAllAction.createMenuAction(getAnathemaModel(), resources));
    mainMenu.addSeparator();
    mainMenu.addMenuItem(AnathemaPrintAction.createMenuAction(getAnathemaModel(), resources));
    mainMenu.addSeparator();
    mainMenu.addMenuItem(AnathemaExitAction.createMenuAction(resources));
    menuBar.getHelpMenu().addMenuItem(new AnathemaAboutAction(resources));
    menuBar.addMenu(createExtraMenu(getAnathemaModel(), resources));
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

  public void exit() {
    System.exit(0);
  }
}