package net.sf.anathema.swing.hero.perspective;

import net.sf.anathema.framework.perspective.ToolBar;
import net.sf.anathema.framework.view.menu.AddToSwingComponent;

import javax.swing.Action;

public class AddToToolbar implements AddToSwingComponent {
  private ToolBar toolbar;

  public AddToToolbar(ToolBar toolbar) {
    this.toolbar = toolbar;
  }

  @Override
  public void add(Action action) {
    toolbar.addTools(action);
  }
}