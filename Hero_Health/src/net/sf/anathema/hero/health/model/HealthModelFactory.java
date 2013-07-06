package net.sf.anathema.hero.health.model;

import net.sf.anathema.character.main.model.abilities.AbilitiesModel;
import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.character.main.model.experience.ExperienceModel;
import net.sf.anathema.character.main.model.health.HealthModel;
import net.sf.anathema.character.main.model.health.HealthModelImpl;
import net.sf.anathema.character.main.model.traits.TraitModel;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.template.TemplateFactory;

@HeroModelAutoCollector
public class HealthModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public HealthModelFactory() {
    super(HealthModel.ID, AbilitiesModel.ID, AttributeModel.ID, TraitModel.ID, ExperienceModel.ID);
  }

  @Override
  public HealthModelImpl create(TemplateFactory templateFactory) {
    return new HealthModelImpl();
  }
}
