package net.sf.anathema.character.main.description.model;

import net.sf.anathema.character.model.CharacterModelAutoCollector;
import net.sf.anathema.character.model.CharacterModelFactory;
import net.sf.anathema.character.model.TemplateFactory;
import net.sf.anathema.lib.util.Identifier;

@CharacterModelAutoCollector
public class CharacterDescriptionFactory implements CharacterModelFactory {

  @Override
  public Identifier getModelId() {
    return CharacterDescription.ID;
  }

  @Override
  public TextualCharacterDescription create(TemplateFactory templateFactory) {
    return new TextualCharacterDescription();
  }
}
