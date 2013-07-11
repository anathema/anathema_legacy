package net.sf.anathema.hero.combos.display.view;

import net.sf.anathema.framework.view.menu.AddToSwingComponent;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;

public class AddToButtonPanel implements AddToSwingComponent {

  private JPanel panel;

  public AddToButtonPanel(JPanel panel) {
    this.panel = panel;
  }

  @Override
  public void add(Action action) {
    JButton button = new JButton(action);
    panel.add(button);
  }
}
