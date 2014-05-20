package net.sf.anathema.hero.dummy.template;

import net.sf.anathema.character.main.template.*;
import net.sf.anathema.character.main.template.abilities.GroupedTraitType;
import net.sf.anathema.character.main.template.creation.BonusPointCosts;
import net.sf.anathema.character.main.template.creation.ICreationPoints;
import net.sf.anathema.character.main.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.main.template.presentation.IPresentationProperties;
import net.sf.anathema.character.main.traits.TraitTemplateCollection;
import net.sf.anathema.character.main.traits.types.AttributeGroupType;
import net.sf.anathema.character.main.traits.types.AttributeType;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.hero.dummy.DummyMundaneCharacterType;
import net.sf.anathema.lib.exception.NotYetImplementedException;

import java.util.ArrayList;
import java.util.List;

public class DummyHeroTemplate implements HeroTemplate {

  public ITraitTemplateCollection traitTemplateCollection = new TraitTemplateCollection(new DummyTraitTemplateFactory());
  public ITemplateType type = new TemplateType(new DummyMundaneCharacterType());
  public DummyCreationPoints creationPoints = new DummyCreationPoints();

  @Override
  public BonusPointCosts getBonusPointCosts() {
    throw new NotYetImplementedException();
  }

  @Override
  public ITemplateType getTemplateType() {
    return type;
  }

  @Override
  public IExperiencePointCosts getExperienceCost() {
    return null;
  }

  @Override
  public IPresentationProperties getPresentationProperties() {
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

  @Override
  public List<ConfiguredModel> getModels() {
    return new ArrayList<>();
  }

  @Override
  public GroupedTraitType[] getAttributeGroups() {
    return new GroupedTraitType[]{new GroupedTraitType(AttributeType.Strength, AttributeGroupType.Physical.getId(), new ArrayList<>()),
            new GroupedTraitType(AttributeType.Dexterity, AttributeGroupType.Physical.getId(), new ArrayList<>()),
            new GroupedTraitType(AttributeType.Stamina, AttributeGroupType.Physical.getId(), new ArrayList<>()),
            new GroupedTraitType(AttributeType.Charisma, AttributeGroupType.Social.getId(), new ArrayList<>()),
            new GroupedTraitType(AttributeType.Manipulation, AttributeGroupType.Social.getId(), new ArrayList<>()),
            new GroupedTraitType(AttributeType.Appearance, AttributeGroupType.Social.getId(), new ArrayList<>()),
            new GroupedTraitType(AttributeType.Perception, AttributeGroupType.Mental.getId(), new ArrayList<>()),
            new GroupedTraitType(AttributeType.Intelligence, AttributeGroupType.Mental.getId(), new ArrayList<>()),
            new GroupedTraitType(AttributeType.Wits, AttributeGroupType.Mental.getId(), new ArrayList<>())};
  }

  public void setCharacterType(CharacterType characterType) {
    this.type = new TemplateType(characterType);
  }
}