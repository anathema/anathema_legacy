package net.sf.anathema.framework.module;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.fx.UiEnvironment;
import net.sf.anathema.framework.view.MenuBar;

import java.util.Collection;

public class AnathemaCoreMenu {

  public void add(Environment environment, UiEnvironment uiEnvironment, IApplicationModel model, MenuBar menubar) {
    Collection<MenuEntry> collection = environment.instantiateOrdered(RegisteredMenuEntry.class, environment, uiEnvironment, model);
    for (MenuEntry menuEntry : collection) {
      menuEntry.addTo(menubar);
    }
  }
}