package net.sf.anathema.character.impl.model;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.caste.ITypedDescriptionType;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public class TypedDescription<T extends ITypedDescriptionType> implements ITypedDescription<T> {

  private final List<IObjectValueChangedListener<T>> typeListeners = new ArrayList<IObjectValueChangedListener<T>>();
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
    T oldType = this.type;
    this.type = type;
    fireTypeChangedEvent(oldType, type);
  }

  public final synchronized void addTypeListener(IObjectValueChangedListener<T> listener) {
    typeListeners.add(listener);
  }

  protected final synchronized void fireTypeChangedEvent(final T oldValue, final T newType) {
    ArrayList<IObjectValueChangedListener<T>> clonedList = new ArrayList<IObjectValueChangedListener<T>>(typeListeners);
    for (IObjectValueChangedListener<T> listener : clonedList) {
      listener.valueChanged(oldValue, newType);
    }
  }
}