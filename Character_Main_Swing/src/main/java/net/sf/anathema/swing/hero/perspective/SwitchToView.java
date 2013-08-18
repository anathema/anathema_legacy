package net.sf.anathema.swing.hero.perspective;

import javax.swing.JPanel;
import java.awt.CardLayout;

public class SwitchToView {

  private final String name;
  private final JPanel viewPanel;
  private final CardLayout viewStack;

  public SwitchToView(String name, JPanel viewPanel, CardLayout viewStack) {
    this.name = name;
    this.viewPanel = viewPanel;
    this.viewStack = viewStack;
  }

  public void execute() {
    viewStack.show(viewPanel, name);
  }
}
