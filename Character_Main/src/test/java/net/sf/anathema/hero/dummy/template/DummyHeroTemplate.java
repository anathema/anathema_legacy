package net.sf.anathema.hero.dummy.template;

import net.sf.anathema.character.main.template.*;
import net.sf.anathema.character.main.template.creation.BonusPointCosts;
import net.sf.anathema.character.main.template.creation.ICreationPoints;
import net.sf.anathema.character.main.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.main.template.presentation.IPresentationProperties;
import net.sf.anathema.character.main.traits.TraitTemplateCollection;
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

  public void setCharacterType(CharacterType characterType) {
    this.type = new TemplateType(characterType);
  }
}