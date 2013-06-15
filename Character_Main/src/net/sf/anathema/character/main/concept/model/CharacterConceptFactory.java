package net.sf.anathema.character.main.concept.model;

import net.sf.anathema.character.model.CharacterModelAutoCollector;
import net.sf.anathema.character.model.CharacterModelFactory;
import net.sf.anathema.character.model.TemplateFactory;
import net.sf.anathema.lib.util.Identifier;

@CharacterModelAutoCollector
public class CharacterConceptFactory implements CharacterModelFactory {

  @Override
  public Identifier getModelId() {
    return CharacterConcept.ID;
  }

  @Override
  public CharacterConcept create(TemplateFactory templateFactory) {
    return new CharacterConceptImpl();
  }
}
