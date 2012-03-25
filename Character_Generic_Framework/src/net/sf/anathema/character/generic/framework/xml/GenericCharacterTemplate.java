package net.sf.anathema.character.generic.framework.xml;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.xml.creation.GenericBonusPointCosts;
import net.sf.anathema.character.generic.framework.xml.creation.GenericCreationPoints;
import net.sf.anathema.character.generic.framework.xml.essence.GenericEssenceTemplate;
import net.sf.anathema.character.generic.framework.xml.experience.GenericExperiencePointCosts;
import net.sf.anathema.character.generic.framework.xml.health.GenericHealthTemplate;
import net.sf.anathema.character.generic.framework.xml.health.IHealthTemplate;
import net.sf.anathema.character.generic.framework.xml.magic.GenericMagicTemplate;
import net.sf.anathema.character.generic.framework.xml.presentation.GenericPresentationTemplate;
import net.sf.anathema.character.generic.framework.xml.rules.GenericAdditionalRules;
import net.sf.anathema.character.generic.framework.xml.trait.GenericTraitTemplateFactory;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.impl.traits.TraitTemplateCollection;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.ITraitTemplateCollection;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.template.creation.IBonusPointCosts;
import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.template.essence.IEssenceTemplate;
import net.sf.anathema.character.generic.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.generic.template.magic.IMagicTemplate;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.lang.clone.ICloneable;

import java.util.ArrayList;
import java.util.List;

public class GenericCharacterTemplate implements ICharacterTemplate, ICloneable<GenericCharacterTemplate> {

  private IAdditionalRules additionalRules = new GenericAdditionalRules();
  private ITemplateType templateType;
  private ITraitTemplateCollection traitTemplateCollection;
  private GenericMagicTemplate magicTemplate;
  private GenericExperiencePointCosts experienceCosts = new GenericExperiencePointCosts();
  private GenericBonusPointCosts bonusPointCosts = new GenericBonusPointCosts();
  private GenericCreationPoints creationPoints = new GenericCreationPoints();
  private GenericEssenceTemplate essenceTemplate;
  private IGroupedTraitType[] abilityGroups;
  private IGroupedTraitType[] attributeGroups;
  private IGroupedTraitType[] yoziGroups;
  private GenericPresentationTemplate presentationTemplate;
  private ICasteCollection casteCollection = new CasteCollection(new ICasteType[0]);
  // This is volatile instead of final to allow clone to be implemented
  private volatile List<IAdditionalTemplate> additionalTemplates = new ArrayList<IAdditionalTemplate>();
  private IHealthTemplate healthTemplate = new GenericHealthTemplate();
  private IExaltedEdition edition;
  private boolean npcOnly;

  @Override
  public boolean isNpcOnly() {
    return npcOnly;
  }

  @Override
  public IGroupedTraitType[] getAbilityGroups() {
    return abilityGroups;
  }

  @Override
  public IGroupedTraitType[] getYoziGroups() {
    return yoziGroups;
  }

  @Override
  public IAdditionalRules getAdditionalRules() {
    return additionalRules;
  }

  @Override
  public IBonusPointCosts getBonusPointCosts() {
    return bonusPointCosts;
  }

  @Override
  public ICasteCollection getCasteCollection() {
    return casteCollection;
  }

  public void setCasteCollection(ICasteCollection casteCollection) {
    this.casteCollection = casteCollection;
  }

  @Override
  public ICreationPoints getCreationPoints() {
    return creationPoints;
  }

  @Override
  public IEssenceTemplate getEssenceTemplate() {
    return essenceTemplate;
  }

  @Override
  public IExperiencePointCosts getExperienceCost() {
    return experienceCosts;
  }

  @Override
  public IPresentationProperties getPresentationProperties() {
    return presentationTemplate;
  }

  @Override
  public ITemplateType getTemplateType() {
    return templateType;
  }

  @Override
  public ITraitTemplateCollection getTraitTemplateCollection() {
    return traitTemplateCollection;
  }

  @Override
  public ITraitType[] getToughnessControllingTraitTypes() {
    return healthTemplate.getToughnessControllingTraits();
  }

  @Override
  public String[] getBaseHealthProviders() {
    return healthTemplate.getBaseHealthProviders();
  }

  @Override
  public IAdditionalTemplate[] getAdditionalTemplates() {
    return additionalTemplates.toArray(new IAdditionalTemplate[additionalTemplates.size()]);
  }

  @Override
  public IMagicTemplate getMagicTemplate() {
    return magicTemplate;
  }

  public void setAbilityGroups(IGroupedTraitType[] abilityGroups) {
    this.abilityGroups = abilityGroups;
  }

  public void setYoziGroups(IGroupedTraitType[] yoziGroups) {
    this.yoziGroups = yoziGroups;
  }

  public void setEssenceTemplate(GenericEssenceTemplate essenceTemplate) {
    this.essenceTemplate = essenceTemplate;
  }

  public void setCreationPoints(GenericCreationPoints creationPoints) {
    this.creationPoints = creationPoints;
  }

  public void setBonusPointCosts(GenericBonusPointCosts bonusPoints) {
    this.bonusPointCosts = bonusPoints;
  }

  public void setExperiencePointCosts(GenericExperiencePointCosts experienceCosts) {
    this.experienceCosts = experienceCosts;
  }

  @Override
  public IExaltedEdition getEdition() {
    return edition;
  }

  public void setTraitFactory(GenericTraitTemplateFactory factory) {
    traitTemplateCollection = new TraitTemplateCollection(factory);
  }

  public void setMagicTemplate(GenericMagicTemplate template) {
    magicTemplate = template;
  }

  public void setPresentationTemplate(GenericPresentationTemplate template) {
    this.presentationTemplate = template;
    if (presentationTemplate != null) {
      presentationTemplate.setParentTemplate(this);
    }
  }

  public void setTemplateType(ITemplateType templateType) {
    this.templateType = templateType;
  }

  @Override
  public GenericCharacterTemplate clone() {
    GenericCharacterTemplate clone;
    try {
      clone = (GenericCharacterTemplate) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new UnreachableCodeReachedException(e);
    }
    clone.additionalRules = new GenericAdditionalRules();
    clone.casteCollection = new CasteCollection(new ICasteType[0]);
    clone.additionalTemplates = new ArrayList<IAdditionalTemplate>();
    clone.healthTemplate = new GenericHealthTemplate();
    if (bonusPointCosts != null) {
      clone.bonusPointCosts = bonusPointCosts.clone();
    }
    if (creationPoints != null) {
      clone.creationPoints = creationPoints.clone();
    }
    if (essenceTemplate != null) {
      clone.essenceTemplate = essenceTemplate.clone();
    }
    if (experienceCosts != null) {
      clone.experienceCosts = experienceCosts.clone();
    }
    if (magicTemplate != null) {
      clone.magicTemplate = magicTemplate.clone();
    }
    if (presentationTemplate != null) {
      clone.presentationTemplate = presentationTemplate.clone();
    }
    return clone;
  }

  @Override
  public IGroupedTraitType[] getAttributeGroups() {
    return attributeGroups;
  }

  public void setAttributeGroups(IGroupedTraitType[] traitTypeGroups) {
    this.attributeGroups = traitTypeGroups;
  }

  public void addAdditionalTemplate(IAdditionalTemplate template) {
    additionalTemplates.add(template);
  }

  public void setHealthTemplate(IHealthTemplate template) {
    this.healthTemplate = template;
  }

  public void setAdditionalRules(GenericAdditionalRules rules) {
    this.additionalRules = rules;
  }

  public void setEdition(IExaltedEdition edition) {
    this.edition = edition;
  }

  public void setNpcOnly() {
    this.npcOnly = true;
  }
}