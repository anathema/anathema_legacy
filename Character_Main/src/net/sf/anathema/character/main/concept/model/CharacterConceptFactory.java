package net.sf.anathema.character.main.concept.model;

import net.sf.anathema.character.model.CharacterModelAutoCollector;
import net.sf.anathema.character.model.CharacterModelFactory;
import net.sf.anathema.character.model.Hero;
import net.sf.anathema.character.model.ModelCreationContext;

@CharacterModelAutoCollector
public class CharacterConceptFactory implements CharacterModelFactory {

  @Override
  public CharacterConcept create(ModelCreationContext context, Hero hero) {
    return new CharacterConceptImpl();
  }
}
