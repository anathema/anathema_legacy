package net.sf.anathema.character.impl.model;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.caste.ITypedDescriptionType;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.lib.control.IChangeListener;

public class TypedDescription<T extends ITypedDescriptionType> implements ITypedDescription<T> {

  private final List<IChangeListener> typeListeners = new ArrayList<IChangeListener>();
  private T type;

  public TypedDescription(T type) {
    this.type = type;
  }

  public final T getType() {
    return type;
  }

  public final void setType(T type) {
    Ensure.ensureNotNull("Type must not be set to null.", type); //$NON-NLS-1$
    if (this.type == type) {
      return;
    }
    this.type = type;
    fireTypeChangedEvent();
  }

  public final synchronized void addChangeListener(IChangeListener listener) {
    typeListeners.add(listener);
  }

  protected final synchronized void fireTypeChangedEvent() {
    ArrayList<IChangeListener> clonedList = new ArrayList<IChangeListener>(typeListeners);
    for (IChangeListener listener : clonedList) {
      listener.changeOccured();
    }
  }
}