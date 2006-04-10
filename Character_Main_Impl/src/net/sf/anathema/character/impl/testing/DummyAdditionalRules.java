package net.sf.anathema.character.impl.testing;

import net.sf.anathema.character.generic.additionalrules.IAdditionalBonusPointPool;
import net.sf.anathema.character.generic.additionalrules.IAdditionalEssencePool;
import net.sf.anathema.character.generic.additionalrules.IAdditionalMagicLearnPool;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.additionalrules.IAdditionalTraitRules;
import net.sf.anathema.character.generic.additionalrules.ITraitCostModifier;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.impl.additional.DefaultTraitCostModifier;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;

public final class DummyAdditionalRules implements IAdditionalRules, IAdditionalTraitRules {

  public IAdditionalBonusPointPool[] getAdditionalBonusPointPools() {
    return new IAdditionalBonusPointPool[0];
  }

  public IAdditionalMagicLearnPool[] getAdditionalMagicLearnPools() {
    return new IAdditionalMagicLearnPool[0];
  }

  public IAdditionalEssencePool[] getAdditionalEssencePools() {
    return new IAdditionalEssencePool[0];
  }

  public boolean isRejected(IBackgroundTemplate backgroundTemplate) {
    return false;
  }

  public boolean isAllowedTraitValue(IGenericTrait trait, ILimitationContext limitationContext) {
    return true;
  }

  public ITraitCostModifier getCostModifier(ITraitType type) {
    return new DefaultTraitCostModifier();
  }

  public String[] getCompulsiveCharmIDs() {
    return new String[0];
  }

  public IAdditionalTraitRules getAdditionalTraitRules() {
    return this;
  }
}