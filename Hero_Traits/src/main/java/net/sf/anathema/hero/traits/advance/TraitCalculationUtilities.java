package net.sf.anathema.hero.traits.advance;

import net.sf.anathema.character.main.library.trait.Trait;

public class TraitCalculationUtilities {

  public static int getCreationCalculationValue(Trait trait) {
    return Math.max(trait.getCurrentValue(), trait.getZeroCalculationValue());
  }

  public static int getCreationCalculationValue(Trait trait, TraitListCreationData map) {
    return Math.max(trait.getCurrentValue(), map.getCalculationBase(trait.getType()));
  }
}
