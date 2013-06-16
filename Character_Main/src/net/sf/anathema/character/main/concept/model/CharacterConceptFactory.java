package net.sf.anathema.character.main.concept.model;

import net.sf.anathema.character.main.lib.SimpleModelTreeEntry;
import net.sf.anathema.character.model.CharacterModelAutoCollector;
import net.sf.anathema.character.model.CharacterModelFactory;
import net.sf.anathema.character.model.TemplateFactory;

@CharacterModelAutoCollector
public class CharacterConceptFactory extends SimpleModelTreeEntry implements CharacterModelFactory {

  public CharacterConceptFactory() {
    super(CharacterConcept.ID);
  }

  @Override
  public CharacterConcept create(TemplateFactory templateFactory) {
    return new CharacterConceptImpl();
  }
}
