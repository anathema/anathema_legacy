package net.sf.anathema.character.main.experience.model;

import net.sf.anathema.character.main.model.initialization.SimpleModelTreeEntry;
import net.sf.anathema.character.main.model.CharacterModelAutoCollector;
import net.sf.anathema.character.main.model.CharacterModelFactory;
import net.sf.anathema.character.main.model.template.TemplateFactory;

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
