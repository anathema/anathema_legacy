package net.sf.anathema.character.equipment.impl.character.view;

import net.sf.anathema.framework.view.menu.AddToSwingComponent;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;

public class AddToSwingPanel implements AddToSwingComponent {
  private JPanel panel;

  public AddToSwingPanel(JPanel panel) {
    this.panel = panel;
  }

  @Override
  public void add(Action action) {
    panel.add(new JButton(action));
  }
}
