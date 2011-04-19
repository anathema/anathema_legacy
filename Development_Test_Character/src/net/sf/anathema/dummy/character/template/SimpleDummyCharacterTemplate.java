package net.sf.anathema.dummy.character.template;

import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateType;
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
  private final IExaltedEdition edition;

  public SimpleDummyCharacterTemplate(CharacterType type, String subtype, IExaltedEdition edition) {
    this.type = type;
    this.subtype = subtype;
    this.edition = edition;
  }

  public IGroupedTraitType[] getAbilityGroups() {
    return null;
  }

  public IGroupedTraitType[] getAttributeGroups() {
    return null;
  }
  
  @Override
  public IGroupedTraitType[] getYoziGroups() {
    return null;
  }

  public IAdditionalRules getAdditionalRules() {
    return null;
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

  public IPresentationProperties getPresentationProperties() {
    return null;
  }

  public ITemplateType getTemplateType() {
    if (subtype == null) {
      return new TemplateType(type);
    }
    return new TemplateType(type, new Identificate(subtype));
  }

  public ITraitTemplateCollection getTraitTemplateCollection() {
    return null;
  }

  public ITraitType[] getToughnessControllingTraitTypes() {
    return null;
  }

  public IAdditionalTemplate[] getAdditionalTemplates() {
    return null;
  }

  public IMagicTemplate getMagicTemplate() {
    return null;
  }

  public IExaltedEdition getEdition() {
    return edition;
  }
  
  public boolean isLegacy()
  {
	  return false;
  }
}