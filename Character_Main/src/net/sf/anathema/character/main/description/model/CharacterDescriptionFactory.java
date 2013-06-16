package net.sf.anathema.character.main.description.model;

import net.sf.anathema.character.main.experience.model.ExperienceModel;
import net.sf.anathema.character.main.model.initialization.SimpleModelTreeEntry;
import net.sf.anathema.character.main.model.CharacterModelAutoCollector;
import net.sf.anathema.character.main.model.CharacterModelFactory;
import net.sf.anathema.character.main.modeltemplate.TemplateFactory;

@CharacterModelAutoCollector
public class CharacterDescriptionFactory extends SimpleModelTreeEntry implements CharacterModelFactory {

  public CharacterDescriptionFactory() {
    super(CharacterDescription.ID, ExperienceModel.ID);
  }

  @Override
  public TextualCharacterDescription create(TemplateFactory templateFactory) {
    return new TextualCharacterDescription();
  }
}
