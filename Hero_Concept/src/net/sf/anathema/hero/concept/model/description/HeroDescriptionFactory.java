package net.sf.anathema.hero.concept.model.description;

import net.sf.anathema.character.main.model.description.HeroDescription;
import net.sf.anathema.character.main.model.experience.ExperienceModel;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.template.TemplateFactory;

@HeroModelAutoCollector
public class HeroDescriptionFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public HeroDescriptionFactory() {
    super(HeroDescription.ID, ExperienceModel.ID);
  }

  @Override
  public HeroDescriptionImpl create(TemplateFactory templateFactory) {
    return new HeroDescriptionImpl();
  }
}
