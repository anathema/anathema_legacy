package net.sf.anathema.character.generic.impl.template.test;

import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITraitTemplateCollection;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.template.creation.IBonusPointCosts;
import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.template.essence.IEssenceTemplate;
import net.sf.anathema.character.generic.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.generic.template.magic.IMagicTemplate;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.util.Identificate;

public class SimpleDummyCharacterTemplate implements ICharacterTemplate {

  private final String subtype;
  private final CharacterType type;
  private final IExaltedRuleSet[] rulesets;

  public SimpleDummyCharacterTemplate(CharacterType type, String subtype, IExaltedRuleSet[] rulesets) {
    this.type = type;
    this.subtype = subtype;
    this.rulesets = rulesets;

  }

  public IGroupedTraitType[] getAbilityGroups() {
    // TODO Auto-generated method stub
    return null;
  }

  public IGroupedTraitType[] getAttributeGroups() {
    // TODO Auto-generated method stub
    return null;
  }

  public IAdditionalRules getAdditionalRules() {
    // TODO Auto-generated method stub
    return null;
  }

  public IBonusPointCosts getBonusPointCosts() {
    // TODO Auto-generated method stub
    return null;
  }

  public ICasteCollection getCasteCollection() {
    // TODO Auto-generated method stub
    return null;
  }

  public ICreationPoints getCreationPoints() {
    // TODO Auto-generated method stub
    return null;
  }

  public IEssenceTemplate getEssenceTemplate() {
    // TODO Auto-generated method stub
    return null;
  }

  public IExperiencePointCosts getExperienceCost() {
    // TODO Auto-generated method stub
    return null;
  }

  public IPresentationProperties getPresentationProperties() {
    // TODO Auto-generated method stub
    return null;
  }

  public TemplateType getTemplateType() {
    if (subtype == null) {
      return new TemplateType(type);
    }
    return new TemplateType(type, new Identificate(subtype));
  }

  public ITraitTemplateCollection getTraitTemplateCollection() {
    // TODO Auto-generated method stub
    return null;
  }

  public ITraitType getToughnessControllingTraitType() {
    // TODO Auto-generated method stub
    return null;
  }

  public IAdditionalTemplate[] getAdditionalTemplates() {
    // TODO Auto-generated method stub
    return null;
  }

  public IMagicTemplate getMagicTemplate() {
    // TODO Auto-generated method stub
    return null;
  }

  public IExaltedRuleSet[] getRuleSets() {
    return rulesets;
  }
}