package net.sf.anathema.character.abyssal;

import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;

public class AbyssalAppearanceLimitation implements ITraitLimitation {

  @Override
  public int getAbsoluteLimit(ILimitationContext limitationContext) {
    int essenceMaximum = limitationContext.getEssenceLimitation().getAbsoluteLimit(limitationContext);
    return Math.max(essenceMaximum, 5);
  }

  @Override
  public int getCurrentMaximum(ILimitationContext limitationContext, boolean modified) {
    int currentEssence = limitationContext.getTraitCollection().getTrait(OtherTraitType.Essence).getCurrentValue();
    int currentEssenceLimitation = Math.max(currentEssence, 5);
    int currentAppearance = limitationContext.getTraitCollection().
            getTrait(AttributeType.Appearance).getCurrentValue();
    if (currentEssence == 4)
      if (currentAppearance <= 2)
        return 1;
      else
        return currentEssenceLimitation;
    if (currentEssence >= 5)
      if (currentAppearance < 2)
        return 0;
      else
        return currentEssenceLimitation;
    return currentEssenceLimitation;
  }

  @Override
  public AbyssalAppearanceLimitation clone() {
    return this;
  }

}
