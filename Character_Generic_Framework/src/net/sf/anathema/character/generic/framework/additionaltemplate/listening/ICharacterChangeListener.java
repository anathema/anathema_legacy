package net.sf.anathema.character.generic.framework.additionaltemplate.listening;

import net.sf.anathema.character.generic.traits.ITraitType;

public interface ICharacterChangeListener {

  public void characterChanged();

  public void traitChanged(ITraitType type);

  public void experiencedChanged(boolean experienced);

  public void casteChanged();
}