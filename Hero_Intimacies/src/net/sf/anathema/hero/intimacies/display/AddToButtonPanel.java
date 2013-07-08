package net.sf.anathema.hero.intimacies.display;

import net.sf.anathema.framework.view.menu.AddToSwingComponent;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Dimension;

import static net.sf.anathema.lib.gui.layout.SwingLayoutUtils.constraintsForImageButton;

public class AddToButtonPanel implements AddToSwingComponent {
  private JPanel buttonPanel;

  public AddToButtonPanel(JPanel buttonPanel) {
    this.buttonPanel = buttonPanel;
  }

  @Override
  public void add(Action action) {
    JButton button = new JButton(action);
    button.setPreferredSize(new Dimension(20, 20));
    buttonPanel.add(button, constraintsForImageButton(button));
  }
}