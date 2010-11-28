package net.sf.anathema.character.generic.framework.additionaltemplate.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;

public interface ICharacterListening {

  public void addChangeListener(ICharacterChangeListener changeListener);
}