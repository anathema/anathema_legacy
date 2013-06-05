package net.sf.anathema.campaign.view.plot;

import net.sf.anathema.framework.view.menu.AddToSwingComponent;

import javax.swing.Action;
import javax.swing.JButton;

public class AddToButton implements AddToSwingComponent {
  private JButton button;

  public AddToButton(JButton button) {
    this.button = button;
  }

  @Override
  public void add(Action action) {
    button.setAction(action);
  }
}
