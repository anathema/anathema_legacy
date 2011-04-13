package net.sf.anathema.development.character;

import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.ITraitTemplateCollection;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.template.abilities.GroupedTraitType;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.template.creation.IBonusPointCosts;
import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.template.essence.IEssenceTemplate;
import net.sf.anathema.character.generic.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.generic.template.magic.IMagicTemplate;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.type.CharacterType;

public class DemoCharacterTemplate implements ICharacterTemplate {

  private final ITemplateType templateType;

  public DemoCharacterTemplate(CharacterType characterType) {
    this.templateType = new TemplateType(characterType);
  }

  @Override
  public IGroupedTraitType[] getAbilityGroups() {
    return null;
  }
  
  @Override
  public IGroupedTraitType[] getYoziGroups() {
    return null;
  }

  @Override
  public IAdditionalRules getAdditionalRules() {
    return null;
  }

  @Override
  public IAdditionalTemplate[] getAdditionalTemplates() {
    return null;
  }

  @Override
  public IGroupedTraitType[] getAttributeGroups() {
    return new IGroupedTraitType[] {
        new GroupedTraitType(AttributeType.Strength, AttributeGroupType.Physical, null),
        new GroupedTraitType(AttributeType.Dexterity, AttributeGroupType.Physical, null),
        new GroupedTraitType(AttributeType.Stamina, AttributeGroupType.Physical, null),
        new GroupedTraitType(AttributeType.Charisma, AttributeGroupType.Social, null),
        new GroupedTraitType(AttributeType.Manipulation, AttributeGroupType.Social, null),
        new GroupedTraitType(AttributeType.Appearance, AttributeGroupType.Social, null),
        new GroupedTraitType(AttributeType.Perception, AttributeGroupType.Mental, null),
        new GroupedTraitType(AttributeType.Intelligence, AttributeGroupType.Mental, null),
        new GroupedTraitType(AttributeType.Wits, AttributeGroupType.Mental, null) };
  }

  @Override
  public IBonusPointCosts getBonusPointCosts() {
    return null;
  }

  @Override
  public ICasteCollection getCasteCollection() {
    return null;
  }

  @Override
  public ICreationPoints getCreationPoints() {
    return null;
  }

  @Override
  public IEssenceTemplate getEssenceTemplate() {
    return null;
  }

  @Override
  public IExperiencePointCosts getExperienceCost() {
    return null;
  }

  @Override
  public IMagicTemplate getMagicTemplate() {
    return null;
  }

  @Override
  public ITraitType getToughnessControllingTraitType() {
    return null;
  }

  @Override
  public ITraitTemplateCollection getTraitTemplateCollection() {
    return null;
  }

  @Override
  public IExaltedEdition getEdition() {
    return null;
  }

  @Override
  public IPresentationProperties getPresentationProperties() {
    return null;
  }

  @Override
  public ITemplateType getTemplateType() {
    return templateType;
  }
  
  public boolean isLegacy()
  {
	  return false;
  }
}