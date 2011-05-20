package net.sf.anathema.character.generic.impl.traits.limitation;

import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;

public class EssenceBasedLimitation implements ITraitLimitation {

  public int getAbsoluteLimit(ILimitationContext limitationContext) {
    int essenceMaximum = limitationContext.getEssenceLimitation().getAbsoluteLimit(limitationContext);
    return Math.max(essenceMaximum, 5);
  }

  public int getCurrentMaximum(ILimitationContext limitationContext, boolean modified) {
	IGenericTrait essence = limitationContext.getTraitCollection()
    							.getTrait(OtherTraitType.Essence);
	int currentEssence = Math.min(essence.getCurrentValue(),
		limitationContext.getEssenceCap(modified));
    int currentEssenceValue = Math.max(currentEssence, 5);
    return Math.min(getAbsoluteLimit(limitationContext), currentEssenceValue);
  }

  @Override
  public EssenceBasedLimitation clone() {
    return this;
  }
}