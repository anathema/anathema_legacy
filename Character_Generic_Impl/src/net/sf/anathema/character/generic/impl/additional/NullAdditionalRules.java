package net.sf.anathema.character.generic.impl.additional;

import net.sf.anathema.character.generic.additionalrules.IAdditionalBonusPointPool;
import net.sf.anathema.character.generic.additionalrules.IAdditionalEssencePool;
import net.sf.anathema.character.generic.additionalrules.IAdditionalMagicLearnPool;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.additionalrules.IAdditionalTraitRules;
import net.sf.anathema.character.generic.additionalrules.ITraitCostModifier;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;

public class NullAdditionalRules implements IAdditionalRules, IAdditionalTraitRules {

  public NullAdditionalRules() {
    // Nothing to do
  }

  public IAdditionalBonusPointPool[] getAdditionalBonusPointPools() {
    return new IAdditionalBonusPointPool[0];
  }

  public IAdditionalMagicLearnPool[] getAdditionalMagicLearnPools() {
    return new IAdditionalMagicLearnPool[0];
  }

  public boolean isRejected(IBackgroundTemplate backgroundTemplate) {
    return false;
  }

  public IAdditionalEssencePool[] getAdditionalEssencePools() {
    return new IAdditionalEssencePool[0];
  }

  public boolean isAllowedTraitValue(IGenericTrait trait, IGenericTraitCollection collection) {
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