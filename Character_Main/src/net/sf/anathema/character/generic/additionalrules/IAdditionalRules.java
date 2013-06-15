package net.sf.anathema.character.generic.additionalrules;

public interface IAdditionalRules {

  IAdditionalBonusPointPool[] getAdditionalBonusPointPools();

  IAdditionalMagicLearnPool[] getAdditionalMagicLearnPools();

  IAdditionalEssencePool[] getAdditionalEssencePools();

  boolean isRevisedIntimacies();

  String[] getCompulsiveCharmIDs();

  IAdditionalTraitRules getAdditionalTraitRules();
}