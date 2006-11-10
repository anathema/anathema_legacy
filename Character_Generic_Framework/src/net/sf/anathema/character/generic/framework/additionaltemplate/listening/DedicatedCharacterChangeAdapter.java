package net.sf.anathema.character.generic.framework.additionaltemplate.listening;

import net.sf.anathema.character.generic.traits.ITraitType;

public class DedicatedCharacterChangeAdapter implements ICharacterChangeListener {

  public final void characterChanged() {
    // Nothing to do
  }

  public void traitChanged(ITraitType type) {
    // Nothing to do
  }

  public void experiencedChanged(boolean experienced) {
    // Nothing to do
  }

  public void casteChanged() {
    //Nothing to do
  }
}