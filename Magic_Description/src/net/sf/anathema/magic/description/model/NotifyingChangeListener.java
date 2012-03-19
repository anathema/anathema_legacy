package net.sf.anathema.magic.description.model;

import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class NotifyingChangeListener implements IChangeListener {
  private final ChangeControl changeControl;

  public NotifyingChangeListener(ChangeControl changeControl) {
    this.changeControl = changeControl;
  }

  @Override
  public void changeOccurred() {
     changeControl.fireChangedEvent();
  }
}
