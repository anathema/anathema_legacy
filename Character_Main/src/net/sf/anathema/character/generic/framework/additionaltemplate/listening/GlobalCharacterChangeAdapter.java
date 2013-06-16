package net.sf.anathema.character.generic.framework.additionaltemplate.listening;

import net.sf.anathema.character.generic.traits.TraitType;

public class GlobalCharacterChangeAdapter implements ICharacterChangeListener {

  @Override
  public void changeOccurred() {
    // Nothing to do
  }

  @Override
  public void traitChanged(TraitType type) {
    changeOccurred();
  }

  @Override
  public void experiencedChanged(boolean experienced) {
    changeOccurred();
  }

  @Override
  public void casteChanged() {
    changeOccurred();
  }
}