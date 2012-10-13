package net.sf.anathema.lib.gui.action;

import net.sf.anathema.lib.control.IChangeListener;

import javax.swing.Icon;

public class RunnableActionConfiguration extends ActionConfiguration implements ActionConfigurationWithRunnable {
  private final Runnable runnable;
  private final Enabler enabler;

  public RunnableActionConfiguration(String name, Icon icon, String tooltip, Runnable runnable, Enabler enabler) {
    super(name,icon, tooltip);
    this.runnable = runnable;
    this.enabler = enabler;
  }

  @Override
  public Runnable getRunnable() {
    return runnable;
  }

  @Override
  public boolean isEnabled() {
    return enabler.isEnabled();
  }

  @Override
  public void whenEnabledStateChanges(IChangeListener listener) {
    enabler.whenEnabledStateChanges(listener);
  }
}