package net.sf.anathema.character.main.model.health;

import net.sf.anathema.character.main.hero.CharacterModelAutoCollector;
import net.sf.anathema.character.main.hero.HeroModelFactory;
import net.sf.anathema.character.main.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.character.main.hero.template.TemplateFactory;
import net.sf.anathema.character.main.model.abilities.AbilityModel;
import net.sf.anathema.character.main.model.attributes.AttributeModel;
import net.sf.anathema.character.main.model.experience.ExperienceModel;
import net.sf.anathema.character.main.model.traits.TraitModel;

@CharacterModelAutoCollector
public class HealthModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public HealthModelFactory() {
    super(HealthModel.ID, AbilityModel.ID, AttributeModel.ID, TraitModel.ID, ExperienceModel.ID);
  }

  @Override
  public HealthModelImpl create(TemplateFactory templateFactory) {
    return new HealthModelImpl();
  }
}
