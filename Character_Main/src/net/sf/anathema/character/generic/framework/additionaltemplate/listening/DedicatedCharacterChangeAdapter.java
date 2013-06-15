package net.sf.anathema.character.generic.framework.additionaltemplate.listening;

import net.sf.anathema.character.generic.traits.ITraitType;

public class DedicatedCharacterChangeAdapter implements ICharacterChangeListener {

  @Override
  public final void changeOccurred() {
    // Nothing to do
  }

  @Override
  public void traitChanged(ITraitType type) {
    // Nothing to do
  }

  @Override
  public void experiencedChanged(boolean experienced) {
    // Nothing to do
  }

  @Override
  public void casteChanged() {
    //Nothing to do
  }
}