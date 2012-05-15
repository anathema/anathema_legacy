package net.sf.anathema.character.generic.framework.additionaltemplate.listening;

import net.sf.anathema.character.generic.traits.ITraitType;

public interface ICharacterChangeListener {

  void characterChanged();

  void traitChanged(ITraitType type);

  void experiencedChanged(boolean experienced);

  void casteChanged();
}