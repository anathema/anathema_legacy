package net.sf.anathema.magic.description.model;

import net.sf.anathema.lib.control.change.IChangeListener;
import org.jmock.example.announcer.Announcer;

public class NotifyingChangeListener implements IChangeListener {
  private final Announcer<IChangeListener> changeControl;

  public NotifyingChangeListener(Announcer<IChangeListener> changeControl) {
    this.changeControl = changeControl;
  }

  @Override
  public void changeOccurred() {
     changeControl.announce().changeOccurred();
  }
}
