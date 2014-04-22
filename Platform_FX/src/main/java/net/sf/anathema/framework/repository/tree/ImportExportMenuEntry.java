package net.sf.anathema.framework.repository.tree;

import javafx.stage.Stage;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.dependencies.Weight;
import net.sf.anathema.framework.module.MenuEntry;
import net.sf.anathema.framework.module.RegisteredMenuEntry;
import net.sf.anathema.framework.view.MenuBar;
import net.sf.anathema.interaction.Command;

@RegisteredMenuEntry
@Weight(weight = 10)
public class ImportExportMenuEntry implements MenuEntry {

  private final Environment environment;
  private final IApplicationModel model;
  private Stage stage;

  public ImportExportMenuEntry(Environment environment, IApplicationModel model, Stage stage) {
    this.environment = environment;
    this.model = model;
    this.stage = stage;
  }

  @Override
  public void addTo(MenuBar menu) {
    Command action = new RepositoryViewAction(model, environment, stage);
    String name = environment.getString("AnathemaCore.Tools.ExportImport.Name") + "\u2026";
    menu.getMainMenu().addMenuItem(action, name);
  }
}
