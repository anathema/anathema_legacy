package net.sf.anathema.character.generic.additionalrules;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface IAdditionalRules {

  public IAdditionalBonusPointPool[] getAdditionalBonusPointPools();

  public IAdditionalMagicLearnPool[] getAdditionalMagicLearnPools();

  public IAdditionalEssencePool[] getAdditionalEssencePools();

  public boolean isRejected(IBackgroundTemplate backgroundTemplate);
  
  public boolean isRevisedIntimacies();

  public ITraitCostModifier getCostModifier(ITraitType type);

  public String[] getCompulsiveCharmIDs();

  public IAdditionalTraitRules getAdditionalTraitRules();
}