package net.sf.anathema.lib.control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ChangeControl {

  private final List<ChangeListener> listeners = new ArrayList<ChangeListener>();
  private final Object source;

  public ChangeControl(Object source) {
    this.source = source;
  }

  private synchronized List<ChangeListener> cloneListeners(){
    return new ArrayList<ChangeListener>(listeners);
  }
  
  public void fireChangedEvent() {
    for (ChangeListener listener:cloneListeners()) {
      listener.stateChanged(new ChangeEvent(source));
    }
  }

  public void addChangeListener(ChangeListener listener) {
    listeners.add(listener);
  }

  public void removeChangeListener(ChangeListener listener) {
    listeners.remove(listener);    
  }
}