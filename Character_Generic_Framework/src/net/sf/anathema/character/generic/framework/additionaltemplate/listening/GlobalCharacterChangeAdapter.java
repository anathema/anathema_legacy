package net.sf.anathema.character.generic.framework.additionaltemplate.listening;

import net.sf.anathema.character.generic.traits.ITraitType;

public class GlobalCharacterChangeAdapter implements ICharacterChangeListener {

  @Override
  public void characterChanged() {
    // Nothing to do
  }

  @Override
  public void traitChanged(ITraitType type) {
    characterChanged();
  }

  @Override
  public void experiencedChanged(boolean experienced) {
    characterChanged();
  }

  @Override
  public void casteChanged() {
    characterChanged();
  }
}