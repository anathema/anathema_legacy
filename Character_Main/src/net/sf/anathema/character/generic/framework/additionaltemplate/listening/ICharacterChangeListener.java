package net.sf.anathema.character.generic.framework.additionaltemplate.listening;

import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.lib.control.IChangeListener;

public interface ICharacterChangeListener extends IChangeListener {

  void traitChanged(TraitType type);

  void experiencedChanged(boolean experienced);

  void casteChanged();
}