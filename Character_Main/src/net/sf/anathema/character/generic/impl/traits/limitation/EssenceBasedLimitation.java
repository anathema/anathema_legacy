package net.sf.anathema.character.generic.impl.traits.limitation;

import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.character.main.model.othertraits.OtherTraitModel;
import net.sf.anathema.character.main.model.othertraits.OtherTraitModelFetcher;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;

public class EssenceBasedLimitation implements ITraitLimitation {

  @Override
  public int getAbsoluteLimit(Hero hero) {
    OtherTraitModel otherTraitModel = getOtherTraitModel(hero);
    ITraitLimitation essenceLimitation = otherTraitModel.getEssenceLimitation();
    int essenceMaximum = essenceLimitation.getAbsoluteLimit(hero);
    return Math.max(essenceMaximum, 5);
  }

  @Override
  public int getCurrentMaximum(Hero hero, boolean modified) {
    OtherTraitModel otherTraitModel = getOtherTraitModel(hero);
    GenericTrait essence = otherTraitModel.getTrait(OtherTraitType.Essence);
    int currentEssence = Math.min(essence.getCurrentValue(), otherTraitModel.getEssenceCap(modified));
    int currentEssenceValue = Math.max(currentEssence, 5);
    return Math.min(getAbsoluteLimit(hero), currentEssenceValue);
  }

  private OtherTraitModel getOtherTraitModel(Hero hero) {
    return OtherTraitModelFetcher.fetch(hero);
  }

  @Override
  public EssenceBasedLimitation clone() {
    try {
      return (EssenceBasedLimitation) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new UnreachableCodeReachedException(e);
    }
  }
}