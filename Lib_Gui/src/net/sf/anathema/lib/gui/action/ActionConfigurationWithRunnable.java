package net.sf.anathema.lib.gui.action;

import net.sf.anathema.lib.control.IChangeListener;

public interface ActionConfigurationWithRunnable extends IActionConfiguration {
  Runnable getRunnable();

  boolean isEnabled();

  void whenEnabledStateChanges(IChangeListener listener);
}