package net.sf.anathema.character.main.xml;

import net.sf.anathema.character.main.template.ConfiguredModel;
import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.template.ITemplateType;
import net.sf.anathema.character.main.template.ITraitTemplateCollection;
import net.sf.anathema.character.main.template.abilities.GroupedTraitType;
import net.sf.anathema.character.main.template.creation.BonusPointCosts;
import net.sf.anathema.character.main.template.creation.ICreationPoints;
import net.sf.anathema.character.main.template.essence.IEssenceTemplate;
import net.sf.anathema.character.main.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.main.template.presentation.IPresentationProperties;
import net.sf.anathema.character.main.traits.TraitTemplateCollection;
import net.sf.anathema.character.main.xml.creation.GenericBonusPointCosts;
import net.sf.anathema.character.main.xml.creation.GenericCreationPoints;
import net.sf.anathema.character.main.xml.essence.GenericEssenceTemplate;
import net.sf.anathema.character.main.xml.experience.GenericExperiencePointCosts;
import net.sf.anathema.character.main.xml.presentation.GenericPresentationTemplate;
import net.sf.anathema.character.main.xml.trait.GenericTraitTemplateFactory;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;
import net.sf.anathema.lib.lang.clone.ICloneable;

import java.util.ArrayList;
import java.util.List;

public class GenericCharacterTemplate implements HeroTemplate, ICloneable<GenericCharacterTemplate> {

  private ITemplateType templateType;
  private ITraitTemplateCollection traitTemplateCollection;
  private GenericExperiencePointCosts experienceCosts = new GenericExperiencePointCosts();
  private GenericBonusPointCosts bonusPointCosts = new GenericBonusPointCosts();
  private GenericCreationPoints creationPoints = new GenericCreationPoints();
  private GenericEssenceTemplate essenceTemplate;
  private GroupedTraitType[] attributeGroups;
  private GenericPresentationTemplate presentationTemplate;
  private final List<ConfiguredModel> models = new ArrayList<>();

  @Override
  public BonusPointCosts getBonusPointCosts() {
    return bonusPointCosts;
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
  public List<ConfiguredModel> getModels() {
    return new ArrayList<>(models);
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

  public void setTraitFactory(GenericTraitTemplateFactory factory) {
    traitTemplateCollection = new TraitTemplateCollection(factory);
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

  @SuppressWarnings("CloneDoesntDeclareCloneNotSupportedException")
  @Override
  public GenericCharacterTemplate clone() {
    GenericCharacterTemplate clone;
    try {
      clone = (GenericCharacterTemplate) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new UnreachableCodeReachedException(e);
    }
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
    if (presentationTemplate != null) {
      clone.presentationTemplate = presentationTemplate.clone();
    }
    return clone;
  }

  @Override
  public GroupedTraitType[] getAttributeGroups() {
    return attributeGroups;
  }

  public void setAttributeGroups(GroupedTraitType[] traitTypeGroups) {
    this.attributeGroups = traitTypeGroups;
  }

  public void addModel(String modelId, String templateId) {
    models.add(new ConfiguredModel(modelId, templateId));
  }
}
