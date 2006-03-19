package net.sf.anathema.character.impl.model;

import net.sf.anathema.character.generic.caste.ITypedDescriptionType;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;


public class StaticTypedDescription<T extends ITypedDescriptionType> implements ITypedDescription<T> {

  private T type;

  public StaticTypedDescription(T type) {
    this.type = type;
  }

  public final T getType() {
    return type;
  }

  public final void setType(T type) {
    throw new UnsupportedOperationException("Type must not be changed"); //$NON-NLS-1$
  }

  public final synchronized void addTypeListener(IObjectValueChangedListener listener) {
    // nothing to do
  }
}