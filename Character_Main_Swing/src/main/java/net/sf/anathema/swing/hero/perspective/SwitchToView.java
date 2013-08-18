package net.sf.anathema.swing.hero.perspective;

import net.sf.anathema.lib.gui.action.SmartAction;

import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Component;

public class SwitchToView extends SmartAction {

  private final String name;
  private final JPanel viewPanel;
  private final CardLayout viewStack;

  public SwitchToView(String name, JPanel viewPanel, CardLayout viewStack) {
    this.name = name;
    this.viewPanel = viewPanel;
    this.viewStack = viewStack;
    setNameWithoutMnemonic(name);
  }

  @Override
  public void execute(Component parent) {
    viewStack.show(viewPanel, name);
  }
}
