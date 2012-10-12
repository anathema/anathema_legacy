package net.sf.anathema.lib.gui.action;

import net.sf.anathema.lib.control.IChangeListener;

import javax.swing.Icon;
import java.awt.Component;

public class RunnableAction extends SmartAction {
  private final Runnable runnable;

  public RunnableAction(Icon icon, Runnable runnable) {
    super(icon);
    this.runnable = runnable;
  }

  public RunnableAction(final ActionConfigurationWithRunnable configuration) {
    super(configuration.getIcon());
    this.runnable = configuration.getRunnable();
    updateEnabled(configuration);
    configuration.whenEnabledStateChanges(new IChangeListener() {
      @Override
      public void changeOccurred() {
        updateEnabled(configuration);
      }
    });
  }

  private void updateEnabled(ActionConfigurationWithRunnable configuration) {
    setEnabled(configuration.isEnabled());
  }

  @Override
  protected void execute(Component parentComponent) {
    runnable.run();
  }
}
