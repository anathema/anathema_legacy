package net.sf.anathema.character.library.trait.view;

import net.sf.anathema.framework.view.menu.AddToSwingComponent;
import net.sf.anathema.lib.gui.layout.SwingLayoutUtils;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Dimension;

public class AddToTraitView implements AddToSwingComponent {
  private JPanel panel;

  public AddToTraitView(JPanel panel) {
    this.panel = panel;
  }

  @Override
  public void add(Action action) {
    JButton button = new JButton(action);
    button.setPreferredSize(new Dimension(20, 20));
    panel.add(button, SwingLayoutUtils.constraintsForImageButton(button));
  }
}
