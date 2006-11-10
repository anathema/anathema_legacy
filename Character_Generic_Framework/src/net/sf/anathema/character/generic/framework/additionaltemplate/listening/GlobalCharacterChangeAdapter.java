package net.sf.anathema.character.generic.framework.additionaltemplate.listening;

import net.sf.anathema.character.generic.traits.ITraitType;

public class GlobalCharacterChangeAdapter implements ICharacterChangeListener {

  public void characterChanged() {
    // Nothing to do
  }

  public void traitChanged(ITraitType type) {
    characterChanged();
  }

  public void experiencedChanged(boolean experienced) {
    characterChanged();
  }

  public void casteChanged() {
    characterChanged();
  }
}