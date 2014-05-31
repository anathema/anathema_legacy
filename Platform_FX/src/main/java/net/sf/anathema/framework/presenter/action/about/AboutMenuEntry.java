package net.sf.anathema.framework.presenter.action.about;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.dependencies.Weight;
import net.sf.anathema.framework.environment.fx.DialogFactory;
import net.sf.anathema.framework.module.MenuEntry;
import net.sf.anathema.framework.module.RegisteredMenuEntry;
import net.sf.anathema.framework.view.MenuBar;
import net.sf.anathema.interaction.Command;

@RegisteredMenuEntry
@Weight(weight = 40)
public class AboutMenuEntry implements MenuEntry {

  private final Environment environment;
  private final DialogFactory dialogFactory;

  @SuppressWarnings("UnusedParameters")
  public AboutMenuEntry(Environment environment, DialogFactory dialogFactory, IApplicationModel model) {
    this.environment = environment;
    this.dialogFactory = dialogFactory;
  }

  @Override
  public void addTo(MenuBar menu) {
    Command action = new AnathemaAboutAction(environment, dialogFactory);
    String name = environment.getString("Help.AboutDialog.Title");
    menu.getHelpMenu().addMenuItem(action, name);
  }
}
