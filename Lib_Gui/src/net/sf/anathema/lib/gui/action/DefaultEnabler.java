package net.sf.anathema.lib.gui.action;

import net.sf.anathema.lib.control.IChangeListener;
import org.jmock.example.announcer.Announcer;

public class DefaultEnabler implements Enabler {
  private final Announcer<IChangeListener> listeners = new Announcer<>(IChangeListener.class);
  private boolean enabled = true;

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
