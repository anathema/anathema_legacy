package net.sf.anathema.development.character.reporting.demo.character;

import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.ITraitTemplateCollection;
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

public class DemoCharacterTemplate implements ICharacterTemplate {

  public IGroupedTraitType[] getAbilityGroups() {
    return null;
  }

  public IAdditionalRules getAdditionalRules() {
    return null;
  }

  public IAdditionalTemplate[] getAdditionalTemplates() {
    return null;
  }

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

  public IBonusPointCosts getBonusPointCosts() {
    return null;
  }

  public ICasteCollection getCasteCollection() {
    return null;
  }

  public ICreationPoints getCreationPoints() {
    return null;
  }

  public IEssenceTemplate getEssenceTemplate() {
    return null;
  }

  public IExperiencePointCosts getExperienceCost() {
    return null;
  }

  public IMagicTemplate getMagicTemplate() {
    return null;
  }

  public ITraitType getToughnessControllingTraitType() {
    return null;
  }

  public ITraitTemplateCollection getTraitTemplateCollection() {
    return null;
  }

  public IExaltedEdition getEdition() {
    return null;
  }

  public IPresentationProperties getPresentationProperties() {
    return null;
  }

  public ITemplateType getTemplateType() {
    return null;
  }

}