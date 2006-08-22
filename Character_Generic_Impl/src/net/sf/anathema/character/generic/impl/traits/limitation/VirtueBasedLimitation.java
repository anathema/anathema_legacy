package net.sf.anathema.character.generic.impl.traits.limitation;

import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;

public class VirtueBasedLimitation implements ITraitLimitation {

  private final ITraitType limitingType;

  public VirtueBasedLimitation(VirtueType limitingType) {
    this.limitingType = limitingType;
  }

  public int getAbsoluteLimit(ILimitationContext limitationContext) {
    return 5;
  }

  public int getCurrentMaximum(ILimitationContext limitationContext) {
    return limitationContext.getTraitCollection().getTrait(limitingType).getCurrentValue();
  }

  @Override
  public VirtueBasedLimitation clone() {
    return this;
  }
}