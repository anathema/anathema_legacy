package net.sf.anathema.framework.module;

import javafx.stage.Stage;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.presenter.action.AnathemaExitAction;
import net.sf.anathema.framework.presenter.action.about.AnathemaAboutAction;
import net.sf.anathema.framework.presenter.action.menu.help.updatecheck.UpdateAction;
import net.sf.anathema.framework.repository.tree.RepositoryViewAction;
import net.sf.anathema.framework.view.MenuBar;
import net.sf.anathema.framework.view.menu.IMenu;
import net.sf.anathema.interaction.Command;

public class AnathemaCoreMenu {

  private Stage stage;

  public AnathemaCoreMenu(Stage stage) {
    this.stage = stage;
  }

  public void add(Environment environment, IApplicationModel model, MenuBar menubar) {
    IMenu mainMenu = menubar.getMainMenu();
    addImportExportEntry(environment, model, mainMenu);
    mainMenu.addSeparator();
    addExitEntry(environment, mainMenu);
    IMenu helpMenu = menubar.getHelpMenu();
    addUpdateEntry(environment, helpMenu);
    addAboutEntry(environment, helpMenu);
  }

  private void addAboutEntry(Resources resources, IMenu helpMenu) {
    Command action = new AnathemaAboutAction(resources, stage);
    String name = resources.getString("Help.AboutDialog.Title");
    helpMenu.addMenuItem(action, name);
  }

  private void addUpdateEntry(Resources resources, IMenu helpMenu) {
    Command action = new UpdateAction(resources);
    String name = resources.getString("Help.UpdateCheck.Title") + "\u2026";
    helpMenu.addMenuItem(action, name);
  }

  private void addExitEntry(Resources resources, IMenu mainMenu) {
    Command action = new AnathemaExitAction();
    String name = resources.getString("AnathemaCore.Tools.Exit.Name");
    mainMenu.addMenuItem(action, name);
  }

  private void addImportExportEntry(Environment environment, IApplicationModel model, IMenu mainMenu) {
    Command action = new RepositoryViewAction(model, environment);
    String name = environment.getString("AnathemaCore.Tools.ExportImport.Name") + "\u2026";
    mainMenu.addMenuItem(action, name);
  }
}