package net.sf.anathema.character.main.experience.model;

import net.sf.anathema.character.main.model.initialization.SimpleModelTreeEntry;
import net.sf.anathema.character.model.CharacterModelAutoCollector;
import net.sf.anathema.character.model.CharacterModelFactory;
import net.sf.anathema.character.model.TemplateFactory;

@CharacterModelAutoCollector
public class ExperienceModelFactory extends SimpleModelTreeEntry implements CharacterModelFactory {

  public ExperienceModelFactory() {
    super(ExperienceModel.ID);
  }

  @Override
  public ExperienceModelImpl create(TemplateFactory templateFactory) {
    return new ExperienceModelImpl();
  }
}
