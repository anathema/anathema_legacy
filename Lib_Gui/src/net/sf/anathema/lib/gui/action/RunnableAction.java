package net.sf.anathema.lib.gui.action;

import javax.swing.Icon;
import java.awt.Component;

public class RunnableAction extends SmartAction {
  private final Runnable runnable;

  public RunnableAction(Icon icon, Runnable runnable) {
    super(icon);
    this.runnable = runnable;
  }

  @Override
  protected void execute(Component parentComponent) {
    runnable.run();
  }
}
