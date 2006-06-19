package net.sf.anathema.character.generic.impl.traits.limitation;

import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;

public class EssenceBasedLimitation implements ITraitLimitation {

  public int getAbsoluteLimit(ILimitationContext limitationContext) {
    int essenceMaximum = limitationContext.getEssenceLimitation() .getAbsoluteLimit(limitationContext);
    return Math.max(essenceMaximum, 5);
  }

  public int getCurrentMaximum(ILimitationContext limitationContext) {
    int currentEssenceValue = Math.max(limitationContext.getTrait(OtherTraitType.Essence).getCurrentValue(), 5);
    return Math.min(getAbsoluteLimit(limitationContext), currentEssenceValue);
  }

  @Override
  public EssenceBasedLimitation clone() {
    return this;
  }
}