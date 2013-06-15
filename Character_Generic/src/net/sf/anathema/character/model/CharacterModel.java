package net.sf.anathema.character.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.lib.util.Identified;

public interface CharacterModel {

  Identified getId();

  void addChangeListener(ICharacterChangeListener changeListener);
}
