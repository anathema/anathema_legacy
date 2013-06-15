package net.sf.anathema.character.generic.framework.additionaltemplate.listening;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.control.IChangeListener;

public interface ICharacterChangeListener extends IChangeListener {

  void traitChanged(ITraitType type);

  void experiencedChanged(boolean experienced);

  void casteChanged();
}