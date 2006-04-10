package net.sf.anathema.lib.control.change;

import java.util.EventListener;

public interface IChangeListener extends EventListener {

  public void changeOccured();
}