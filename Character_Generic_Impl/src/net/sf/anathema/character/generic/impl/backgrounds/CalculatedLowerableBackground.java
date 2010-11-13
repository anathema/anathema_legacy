package net.sf.anathema.character.generic.impl.backgrounds;

import net.sf.anathema.character.generic.traits.LowerableState;

public abstract class CalculatedLowerableBackground extends AbstractBackgroundTemplate {

  public CalculatedLowerableBackground(String id) {
    super(id);
  }

  public final LowerableState getExperiencedState() {
    return LowerableState.LowerableRegain;
  }
}