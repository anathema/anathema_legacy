package net.sf.anathema.framework.module;

import javafx.stage.Stage;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.view.MenuBar;

import java.util.Collection;

public class AnathemaCoreMenu {

  private Stage stage;

  public AnathemaCoreMenu(Stage stage) {
    this.stage = stage;
  }

  public void add(Environment environment, IApplicationModel model, MenuBar menubar) {
    Collection<MenuEntry> collection = environment.instantiateOrdered(RegisteredMenuEntry.class, environment, model, stage);
    for (MenuEntry menuEntry : collection) {
      menuEntry.addTo(menubar);
    }
  }
}