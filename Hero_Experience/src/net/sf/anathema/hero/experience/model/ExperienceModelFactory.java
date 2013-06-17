package net.sf.anathema.hero.experience.model;

import net.sf.anathema.character.main.model.experience.ExperienceModel;
import net.sf.anathema.character.main.hero.HeroModelFactory;
import net.sf.anathema.character.main.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.character.main.hero.CharacterModelAutoCollector;
import net.sf.anathema.character.main.hero.template.TemplateFactory;

@CharacterModelAutoCollector
public class ExperienceModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public ExperienceModelFactory() {
    super(ExperienceModel.ID);
  }

  @Override
  public ExperienceModelImpl create(TemplateFactory templateFactory) {
    return new ExperienceModelImpl();
  }
}
