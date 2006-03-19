package net.sf.anathema.lib.control.process;

import java.util.ArrayList;
import java.util.List;

public class ProcessControl {

  private final List <IProcessListener> processListener = new ArrayList<IProcessListener> ();

  public synchronized void addProcessListener(IProcessListener listener) {
    processListener.add(listener);
  }

  public synchronized void removeProcessListener(IProcessListener listener) {
    processListener.remove(listener);
  }

  public synchronized void fireProcessPerformed() {
    List <IProcessListener> clonedListeners = new ArrayList<IProcessListener> (processListener);
    for (IProcessListener listener : clonedListeners) {
      listener.processPerformed();
    }
  }
  
}
