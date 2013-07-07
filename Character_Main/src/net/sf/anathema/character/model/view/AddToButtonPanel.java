package net.sf.anathema.character.model.view;

import net.sf.anathema.framework.view.menu.AddToSwingComponent;
import net.sf.anathema.lib.gui.toolbar.ToolBarUtilities;

import javax.swing.Action;
import javax.swing.JPanel;

public class AddToButtonPanel implements AddToSwingComponent {
  private JPanel panel;

  public AddToButtonPanel(JPanel panel) {
    this.panel = panel;
  }

  @Override
  public void add(Action action) {
    panel.add(ToolBarUtilities.createToolBarButton(action));
  }
}