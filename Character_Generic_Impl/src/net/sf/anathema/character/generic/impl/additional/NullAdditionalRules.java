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

  @Override
  public IAdditionalBonusPointPool[] getAdditionalBonusPointPools() {
    return new IAdditionalBonusPointPool[0];
  }

  @Override
  public IAdditionalMagicLearnPool[] getAdditionalMagicLearnPools() {
    return new IAdditionalMagicLearnPool[0];
  }

  @Override
  public boolean isRejected(IBackgroundTemplate backgroundTemplate) {
    return false;
  }
  
  @Override
  public boolean isRevisedIntimacies() {
    return false;
  }
  
  @Override
  public boolean isWillpowerVirtueBased() {
    return true;
  }

  @Override
  public IAdditionalEssencePool[] getAdditionalEssencePools() {
    return new IAdditionalEssencePool[0];
  }

  @Override
  public boolean isAllowedTraitValue(IGenericTrait trait, IGenericTraitCollection collection) {
    return true;
  }

  @Override
  public ITraitCostModifier getCostModifier(ITraitType type) {
    return new DefaultTraitCostModifier();
  }

  @Override
  public String[] getCompulsiveCharmIDs() {
    return new String[0];
  }

  @Override
  public IAdditionalTraitRules getAdditionalTraitRules() {
    return this;
  }
}