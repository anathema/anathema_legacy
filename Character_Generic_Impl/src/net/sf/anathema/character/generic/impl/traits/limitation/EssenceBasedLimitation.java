package net.sf.anathema.character.generic.impl.traits.limitation;

import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;

public class EssenceBasedLimitation implements ITraitLimitation {

  @Override
  public int getAbsoluteLimit(ILimitationContext limitationContext) {
    int essenceMaximum = limitationContext.getEssenceLimitation().getAbsoluteLimit(limitationContext);
    return Math.max(essenceMaximum, 5);
  }

  @Override
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
    try {
      return (EssenceBasedLimitation)super.clone();
    } catch (CloneNotSupportedException e) {
      throw new UnreachableCodeReachedException(e);
    }
  }
}