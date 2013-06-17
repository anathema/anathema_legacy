package net.sf.anathema.hero.concept.model.description;

import net.sf.anathema.character.main.model.description.HeroDescription;
import net.sf.anathema.character.main.model.experience.ExperienceModel;
import net.sf.anathema.character.main.hero.HeroModelFactory;
import net.sf.anathema.character.main.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.character.main.hero.CharacterModelAutoCollector;
import net.sf.anathema.character.main.hero.template.TemplateFactory;

@CharacterModelAutoCollector
public class HeroDescriptionFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public HeroDescriptionFactory() {
    super(HeroDescription.ID, ExperienceModel.ID);
  }

  @Override
  public HeroDescriptionImpl create(TemplateFactory templateFactory) {
    return new HeroDescriptionImpl();
  }
}
