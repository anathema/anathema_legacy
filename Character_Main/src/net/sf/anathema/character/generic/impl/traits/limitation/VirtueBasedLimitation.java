package net.sf.anathema.character.generic.impl.traits.limitation;

import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;

public class VirtueBasedLimitation implements ITraitLimitation {

  private final TraitType limitingType;

  public VirtueBasedLimitation(VirtueType limitingType) {
    this.limitingType = limitingType;
  }

  @Override
  public int getAbsoluteLimit(ILimitationContext limitationContext) {
    return 5;
  }

  @Override
  public int getCurrentMaximum(ILimitationContext limitationContext, boolean modified) {
    return limitationContext.getTraitCollection().getTrait(limitingType).getCurrentValue();
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