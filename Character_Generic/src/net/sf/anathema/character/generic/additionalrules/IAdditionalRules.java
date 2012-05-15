package net.sf.anathema.character.generic.additionalrules;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface IAdditionalRules {

  IAdditionalBonusPointPool[] getAdditionalBonusPointPools();

  IAdditionalMagicLearnPool[] getAdditionalMagicLearnPools();

  IAdditionalEssencePool[] getAdditionalEssencePools();

  boolean isRejected(IBackgroundTemplate backgroundTemplate);
  
  boolean isRevisedIntimacies();

  ITraitCostModifier getCostModifier(ITraitType type);

  String[] getCompulsiveCharmIDs();

  IAdditionalTraitRules getAdditionalTraitRules();
}