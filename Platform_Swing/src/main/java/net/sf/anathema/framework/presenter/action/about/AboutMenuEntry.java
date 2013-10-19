package net.sf.anathema.framework.presenter.action.about;

import javafx.stage.Stage;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.dependencies.Weight;
import net.sf.anathema.framework.module.MenuEntry;
import net.sf.anathema.framework.module.RegisteredMenuEntry;
import net.sf.anathema.framework.view.MenuBar;
import net.sf.anathema.interaction.Command;

@RegisteredMenuEntry
@Weight(weight = 40)
public class AboutMenuEntry implements MenuEntry {

  private final Environment environment;
  private final Stage stage;

  @SuppressWarnings("UnusedParameters")
  public AboutMenuEntry(Environment environment, IApplicationModel model, Stage stage) {
    this.environment = environment;
    this.stage = stage;
  }

  @Override
  public void addTo(MenuBar menu) {
    Command action = new AnathemaAboutAction(environment, stage);
    String name = environment.getString("Help.AboutDialog.Title");
    menu.getHelpMenu().addMenuItem(action, name);
  }
}
