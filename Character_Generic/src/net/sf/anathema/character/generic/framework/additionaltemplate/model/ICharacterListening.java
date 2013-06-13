package net.sf.anathema.character.generic.framework.additionaltemplate.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;

public interface ICharacterListening {

  void addChangeListener(ICharacterChangeListener changeListener);
}