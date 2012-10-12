package net.sf.anathema.lib.gui.action;

import net.sf.anathema.lib.control.IChangeListener;
import org.jmock.example.announcer.Announcer;

import javax.swing.Icon;

public class RunnableActionConfiguration extends ActionConfiguration implements ActionConfigurationWithRunnable {
  private final Announcer<IChangeListener> listeners = new Announcer<IChangeListener>(IChangeListener.class);
  private final Runnable runnable;
  private boolean enabled = true;

  public RunnableActionConfiguration(String name, Icon icon, String tooltip, Runnable runnable) {
    super(name,icon, tooltip);
    this.runnable = runnable;
  }

  @Override
  public Runnable getRunnable() {
    return runnable;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  @Override
  public void whenEnabledStateChanges(IChangeListener listener) {
    listeners.addListener(listener);
  }

  protected void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }
}