package net.sf.anathema.framework.presenter.action.updatecheck;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.dependencies.Weight;
import net.sf.anathema.framework.environment.fx.UiEnvironment;
import net.sf.anathema.framework.module.MenuEntry;
import net.sf.anathema.framework.module.RegisteredMenuEntry;
import net.sf.anathema.framework.view.MenuBar;
import net.sf.anathema.interaction.Command;

@RegisteredMenuEntry
@Weight(weight = 30)
public class UpdateMenuEntry implements MenuEntry {

  private final Environment environment;
  private final UiEnvironment uiEnvironment;

  @SuppressWarnings("UnusedParameters")
  public UpdateMenuEntry(Environment environment, UiEnvironment uiEnvironment, IApplicationModel model) {
    this.environment = environment;
    this.uiEnvironment = uiEnvironment;
  }

  @Override
  public void addTo(MenuBar menu) {
    Command action = new UpdateAction(environment,uiEnvironment);
    String name = environment.getString("Help.UpdateCheck.Title") + "\u2026";
    menu.getHelpMenu().addMenuItem(action, name);
  }
}