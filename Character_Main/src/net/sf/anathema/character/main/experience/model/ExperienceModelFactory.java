package net.sf.anathema.character.main.experience.model;

import net.sf.anathema.character.model.CharacterModelAutoCollector;
import net.sf.anathema.character.model.CharacterModelFactory;
import net.sf.anathema.character.model.Hero;
import net.sf.anathema.character.model.ModelCreationContext;

@CharacterModelAutoCollector
public class ExperienceModelFactory implements CharacterModelFactory {

  @Override
  public ExperienceModel create(ModelCreationContext context, Hero hero) {
    return new ExperienceModelImpl();
  }
}
