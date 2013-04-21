package net.sf.anathema.character.generic.template;

import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.template.abilities.GroupedTraitType;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.template.creation.BonusPointCosts;
import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.template.essence.IEssenceTemplate;
import net.sf.anathema.character.generic.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.generic.template.magic.IMagicTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface ICharacterTemplate extends ICharacterExternalsTemplate {

  GroupedTraitType[] getAbilityGroups();

  GroupedTraitType[] getAttributeGroups();
  
  IAdditionalRules getAdditionalRules();

  BonusPointCosts getBonusPointCosts();

  ICasteCollection getCasteCollection();

  ICreationPoints getCreationPoints();

  IEssenceTemplate getEssenceTemplate();

  IExperiencePointCosts getExperienceCost();

  ITraitTemplateCollection getTraitTemplateCollection();

  ITraitType[] getToughnessControllingTraitTypes();
  
  String[] getBaseHealthProviders();

  IAdditionalTemplate[] getAdditionalTemplates();

  IMagicTemplate getMagicTemplate();

  boolean isNpcOnly();
  
  boolean isCustomTemplate();
}