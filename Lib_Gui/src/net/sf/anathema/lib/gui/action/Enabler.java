package net.sf.anathema.lib.gui.action;

import net.sf.anathema.lib.control.IChangeListener;

public interface Enabler {
  boolean isEnabled();

  void whenEnabledStateChanges(IChangeListener listener);
}
