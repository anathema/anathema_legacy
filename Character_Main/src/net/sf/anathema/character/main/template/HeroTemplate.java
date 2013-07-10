package net.sf.anathema.character.main.template;

import net.sf.anathema.character.main.template.abilities.GroupedTraitType;
import net.sf.anathema.character.main.template.creation.BonusPointCosts;
import net.sf.anathema.character.main.template.creation.ICreationPoints;
import net.sf.anathema.character.main.template.essence.IEssenceTemplate;
import net.sf.anathema.character.main.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.main.template.magic.IMagicTemplate;
import net.sf.anathema.character.main.traits.TraitType;

import java.util.List;

public interface HeroTemplate extends ICharacterExternalsTemplate {

  GroupedTraitType[] getAbilityGroups();

  GroupedTraitType[] getAttributeGroups();

  BonusPointCosts getBonusPointCosts();

  ICreationPoints getCreationPoints();

  IEssenceTemplate getEssenceTemplate();

  IExperiencePointCosts getExperienceCost();

  ITraitTemplateCollection getTraitTemplateCollection();

  TraitType[] getToughnessControllingTraitTypes();

  String[] getBaseHealthProviders();

  List<ConfiguredModel> getModels();

  IMagicTemplate getMagicTemplate();

  boolean isCustomTemplate();
}