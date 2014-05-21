package net.sf.anathema.hero.traits.advance;

import net.sf.anathema.hero.traits.model.Trait;

public class TraitCalculationUtilities {

  public static int getCreationCalculationValue(Trait trait, TraitListCreationData map) {
    return Math.max(trait.getCurrentValue(), map.getCalculationBase(trait.getType()));
  }
}
