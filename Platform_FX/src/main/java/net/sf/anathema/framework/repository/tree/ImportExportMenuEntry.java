package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.dependencies.Weight;
import net.sf.anathema.framework.environment.fx.UiEnvironment;
import net.sf.anathema.framework.module.MenuEntry;
import net.sf.anathema.framework.module.RegisteredMenuEntry;
import net.sf.anathema.framework.view.MenuBar;
import net.sf.anathema.interaction.Command;

@RegisteredMenuEntry
@Weight(weight = 10)
public class ImportExportMenuEntry implements MenuEntry {

  private final Environment environment;
  private final IApplicationModel model;
  private final UiEnvironment uiEnvironment;

  public ImportExportMenuEntry(Environment environment, UiEnvironment uiEnvironment, IApplicationModel model) {
    this.environment = environment;
    this.uiEnvironment = uiEnvironment;
    this.model = model;
  }

  @Override
  public void addTo(MenuBar menu) {
    Command action = new RepositoryViewAction(model, environment, uiEnvironment);
    String name = environment.getString("AnathemaCore.Tools.ExportImport.Name") + "\u2026";
    menu.getMainMenu().addMenuItem(action, name);
  }
}
