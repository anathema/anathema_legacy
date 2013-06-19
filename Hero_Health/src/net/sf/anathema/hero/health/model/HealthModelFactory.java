package net.sf.anathema.hero.health.model;

import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.template.TemplateFactory;
import net.sf.anathema.character.main.model.abilities.AbilityModel;
import net.sf.anathema.character.main.model.attributes.AttributeModel;
import net.sf.anathema.character.main.model.experience.ExperienceModel;
import net.sf.anathema.character.main.model.health.HealthModel;
import net.sf.anathema.character.main.model.health.HealthModelImpl;
import net.sf.anathema.character.main.model.traits.TraitModel;

@HeroModelAutoCollector
public class HealthModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public HealthModelFactory() {
    super(HealthModel.ID, AbilityModel.ID, AttributeModel.ID, TraitModel.ID, ExperienceModel.ID);
  }

  @Override
  public HealthModelImpl create(TemplateFactory templateFactory) {
    return new HealthModelImpl();
  }
}
