package net.sf.anathema.lib.gui.dialog.input;

import org.jmock.example.announcer.Announcer;

public abstract class AbstractSmartDialogPanel implements ISmartDialogPanel {

  private transient Announcer<RequestFinishListener> listeners = Announcer.to(RequestFinishListener.class);

  protected final void fireRequestFinish() {
    listeners.announce().requestFinish();
  }

}