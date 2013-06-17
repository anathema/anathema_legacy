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
import net.sf.anathema.character.generic.traits.TraitType;

import java.util.List;

public interface HeroTemplate extends ICharacterExternalsTemplate {

  GroupedTraitType[] getAbilityGroups();

  GroupedTraitType[] getAttributeGroups();

  IAdditionalRules getAdditionalRules();

  BonusPointCosts getBonusPointCosts();

  ICasteCollection getCasteCollection();

  ICreationPoints getCreationPoints();

  IEssenceTemplate getEssenceTemplate();

  IExperiencePointCosts getExperienceCost();

  ITraitTemplateCollection getTraitTemplateCollection();

  TraitType[] getToughnessControllingTraitTypes();

  String[] getBaseHealthProviders();

  IAdditionalTemplate[] getAdditionalTemplates();

  List<String> getModels();

  IMagicTemplate getMagicTemplate();

  boolean isCustomTemplate();
}