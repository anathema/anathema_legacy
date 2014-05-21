package net.sf.anathema.hero.traits.model.limitation;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.types.VirtueType;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;

public class VirtueBasedLimitation implements TraitLimitation {

  private final TraitType limitingType;

  public VirtueBasedLimitation(VirtueType limitingType) {
    this.limitingType = limitingType;
  }

  @Override
  public int getAbsoluteLimit(Hero hero) {
    return 5;
  }

  @Override
  public int getCurrentMaximum(Hero hero, boolean modified) {
    return TraitModelFetcher.fetch(hero).getTrait(limitingType).getCurrentValue();
  }

  @Override
  public VirtueBasedLimitation clone() {
    try {
      return (VirtueBasedLimitation) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new UnreachableCodeReachedException(e);
    }
  }
}