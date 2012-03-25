package net.sf.anathema.character.generic.dummy.template;

import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.impl.additional.NullAdditionalRules;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.impl.template.points.UnsupportedCreationPoints;
import net.sf.anathema.character.generic.impl.traits.ExaltTraitTemplateFactory;
import net.sf.anathema.character.generic.impl.traits.TraitTemplateCollection;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.ITraitTemplateCollection;
import net.sf.anathema.character.generic.template.IUnsupportedTemplate;
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
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.YoziType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.util.Identificate;

public class DummyUnsupportedTemplate implements IUnsupportedTemplate, ICharacterTemplate {

  private final String subtype;
  private final CharacterType type;
  private final IExaltedEdition edition;
  private final ICreationPoints creationPoints = new UnsupportedCreationPoints();
  private final ITraitTemplateCollection traitTemplateCollection = new TraitTemplateCollection(
      new ExaltTraitTemplateFactory());
  private NullAdditionalRules additionalRules = new NullAdditionalRules();

  public DummyUnsupportedTemplate(CharacterType type, String subtype, IExaltedEdition edition) {
    this.type = type;
    this.subtype = subtype;
    this.edition = edition;
  }

  @Override
  public IGroupedTraitType[] getAbilityGroups() {
    throw new NotYetImplementedException();
  }

  @Override
  public IPresentationProperties getPresentationProperties() {
    throw new NotYetImplementedException();
  }

  @Override
  public ITemplateType getTemplateType() {
    if (subtype == null) {
      return new TemplateType(type);
    }
    return new TemplateType(type, new Identificate(subtype));
  }

  @Override
  public IExaltedEdition getEdition() {
    return edition;
  }

  @Override
  public IMagicTemplate getMagicTemplate() {
    throw new NotYetImplementedException();
  }
  
  @Override
  public String[] getBaseHealthProviders()
  {
	  return new String[0];
  }

  @Override
  public IBonusPointCosts getBonusPointCosts() {
    throw new NotYetImplementedException();
  }

  @Override
  public IEssenceTemplate getEssenceTemplate() {
    throw new NotYetImplementedException();
  }

  @Override
  public IExperiencePointCosts getExperienceCost() {
    throw new NotYetImplementedException();
  }

  @Override
  public ICreationPoints getCreationPoints() {
    return creationPoints;
  }

  @Override
  public ITraitTemplateCollection getTraitTemplateCollection() {
    return traitTemplateCollection;
  }

  public ICasteType[] getAllCasteTypes() {
    return new ICasteType[0];
  }

  @Override
  public IAdditionalRules getAdditionalRules() {
    return additionalRules;
  }

  @Override
  public final ICasteCollection getCasteCollection() {
    return new CasteCollection(getAllCasteTypes());
  }

  @Override
  public ITraitType[] getToughnessControllingTraitTypes() {
    return new ITraitType[]{AbilityType.Resistance};
  }

  @Override
  public IAdditionalTemplate[] getAdditionalTemplates() {
    return new IAdditionalTemplate[0];
  }

  @Override
  public IGroupedTraitType[] getAttributeGroups() {
    return new IGroupedTraitType[]{new GroupedTraitType(AttributeType.Strength, AttributeGroupType.Physical.getId(),
            null), new GroupedTraitType(AttributeType.Dexterity, AttributeGroupType.Physical.getId(),
            null), new GroupedTraitType(AttributeType.Stamina, AttributeGroupType.Physical.getId(),
            null), new GroupedTraitType(AttributeType.Charisma, AttributeGroupType.Social.getId(),
            null), new GroupedTraitType(AttributeType.Manipulation, AttributeGroupType.Social.getId(),
            null), new GroupedTraitType(AttributeType.Appearance, AttributeGroupType.Social.getId(),
            null), new GroupedTraitType(AttributeType.Perception, AttributeGroupType.Mental.getId(),
            null), new GroupedTraitType(AttributeType.Intelligence, AttributeGroupType.Mental.getId(),
            null), new GroupedTraitType(AttributeType.Wits, AttributeGroupType.Mental.getId(), null),};
  }

  @Override
  public IGroupedTraitType[] getYoziGroups() {
    return new IGroupedTraitType[]{new GroupedTraitType(YoziType.Malfeas, YoziType.Malfeas.getId(),
            null), new GroupedTraitType(YoziType.Cecelyne, YoziType.Cecelyne.getId(), null), new GroupedTraitType(
            YoziType.SheWhoLivesInHerName, YoziType.SheWhoLivesInHerName.getId(), null), new GroupedTraitType(
            YoziType.Adorjan, YoziType.Adorjan.getId(), null), new GroupedTraitType(YoziType.EbonDragon,
            YoziType.EbonDragon.getId(), null), new GroupedTraitType(YoziType.Kimbery, YoziType.Kimbery.getId(), null)};
  }

  @Override
  public boolean isNpcOnly() {
    return false;
  }
}