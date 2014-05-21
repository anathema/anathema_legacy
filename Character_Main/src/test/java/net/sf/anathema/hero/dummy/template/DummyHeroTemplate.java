package net.sf.anathema.hero.dummy.template;

import net.sf.anathema.character.main.template.ConfiguredModel;
import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.template.TemplateType;
import net.sf.anathema.character.main.template.TemplateTypeImpl;
import net.sf.anathema.character.main.template.creation.BonusPointCosts;
import net.sf.anathema.character.main.template.creation.ICreationPoints;
import net.sf.anathema.character.main.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.hero.dummy.DummyMundaneCharacterType;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.util.ArrayList;
import java.util.List;

public class DummyHeroTemplate implements HeroTemplate {

  public TemplateType type = new TemplateTypeImpl(new DummyMundaneCharacterType(), new SimpleIdentifier("Test"));
  public DummyCreationPoints creationPoints = new DummyCreationPoints();

  @Override
  public BonusPointCosts getBonusPointCosts() {
    throw new NotYetImplementedException();
  }

  @Override
  public TemplateType getTemplateType() {
    return type;
  }

  @Override
  public IExperiencePointCosts getExperienceCost() {
    return null;
  }

  @Override
  public ICreationPoints getCreationPoints() {
    return creationPoints;
  }

  @Override
  public List<ConfiguredModel> getModels() {
    return new ArrayList<>();
  }

  public void setCharacterType(CharacterType characterType) {
    this.type = new TemplateTypeImpl(characterType, new SimpleIdentifier("Test"));
  }
}