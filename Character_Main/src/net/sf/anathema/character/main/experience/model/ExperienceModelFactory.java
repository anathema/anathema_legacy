package net.sf.anathema.character.main.experience.model;

import net.sf.anathema.character.model.CharacterModelAutoCollector;
import net.sf.anathema.character.model.CharacterModelFactory;
import net.sf.anathema.character.model.TemplateFactory;
import net.sf.anathema.lib.util.Identifier;

@CharacterModelAutoCollector
public class ExperienceModelFactory implements CharacterModelFactory {

  @Override
  public Identifier getModelId() {
    return ExperienceModel.ID;
  }

  @Override
  public ExperienceModel create(TemplateFactory templateFactory) {
    return new ExperienceModelImpl();
  }
}
