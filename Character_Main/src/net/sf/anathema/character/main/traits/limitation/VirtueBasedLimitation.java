package net.sf.anathema.character.main.traits.limitation;

import net.sf.anathema.character.main.template.ITraitLimitation;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.types.VirtueType;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;

public class VirtueBasedLimitation implements ITraitLimitation {

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