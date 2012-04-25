package net.sf.anathema.character.impl.model;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.caste.ITypedDescriptionType;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class TypedDescription<T extends ITypedDescriptionType> implements ITypedDescription<T> {

  private final ChangeControl control = new ChangeControl();
  private T type;

  public TypedDescription(T type) {
    this.type = type;
  }

  @Override
  public final T getType() {
    return type;
  }

  @Override
  public final void setType(T type) {
    Ensure.ensureNotNull("Type must not be set to null.", type); //$NON-NLS-1$
    if (this.type == type) {
      return;
    }
    this.type = type;
    fireTypeChangedEvent();
  }

  @Override
  public final void addChangeListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }

  private void fireTypeChangedEvent() {
    control.fireChangedEvent();
  }
}