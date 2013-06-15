package net.sf.anathema.character.main.description.model;

import net.sf.anathema.character.model.CharacterModelAutoCollector;
import net.sf.anathema.character.model.CharacterModelFactory;
import net.sf.anathema.character.model.TemplateFactory;
import net.sf.anathema.lib.util.Identified;

@CharacterModelAutoCollector
public class CharacterDescriptionFactory implements CharacterModelFactory {

  @Override
  public Identified getModelId() {
    return CharacterDescription.ID;
  }

  @Override
  public TextualCharacterDescription create(TemplateFactory templateFactory) {
    return new TextualCharacterDescription();
  }
}
