package net.sf.anathema.character.generic.template;

import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.template.creation.IBonusPointCosts;
import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.template.essence.IEssenceTemplate;
import net.sf.anathema.character.generic.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.generic.template.magic.IMagicTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface ICharacterTemplate extends ICharacterExternalsTemplate {

  public IGroupedTraitType[] getAbilityGroups();

  public IGroupedTraitType[] getAttributeGroups();
  
  public IGroupedTraitType[] getYoziGroups();

  public IAdditionalRules getAdditionalRules();

  public IBonusPointCosts getBonusPointCosts();

  public ICasteCollection getCasteCollection();

  public ICreationPoints getCreationPoints();

  public IEssenceTemplate getEssenceTemplate();

  public IExperiencePointCosts getExperienceCost();

  public ITraitTemplateCollection getTraitTemplateCollection();

  public ITraitType[] getToughnessControllingTraitTypes();

  public IAdditionalTemplate[] getAdditionalTemplates();

  public IMagicTemplate getMagicTemplate();
}