package net.sf.anathema.view.interaction;

import net.sf.anathema.framework.view.menu.AddToSwingComponent;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;

public class AddToComponent implements AddToSwingComponent {
  private JComponent panel;

  public AddToComponent(JComponent panel) {
    this.panel = panel;
  }

  @Override
  public void add(Action action) {
    panel.add(new JButton(action));
  }
}