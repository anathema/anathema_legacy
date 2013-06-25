package net.sf.anathema.character.model;

import net.sf.anathema.character.generic.caste.ITypedDescriptionType;
import net.sf.anathema.lib.control.ChangeListener;

public interface ITypedDescription<T extends ITypedDescriptionType> {

  T getType();

  void setType(T type);

  void addChangeListener(ChangeListener listener);
}