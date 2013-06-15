package net.sf.anathema.character.main.description.model;

import net.sf.anathema.character.model.CharacterModelAutoCollector;
import net.sf.anathema.character.model.CharacterModelFactory;
import net.sf.anathema.character.model.CharacterModelGroup;
import net.sf.anathema.character.model.Hero;
import net.sf.anathema.character.model.ModelCreationContext;
import net.sf.anathema.character.model.ModelGroup;
import net.sf.anathema.initialization.reflections.Weight;

@CharacterModelAutoCollector
@Weight(weight = 0)
@ModelGroup(group = CharacterModelGroup.Outline)
public class CharacterDescriptionFactory implements CharacterModelFactory {

  @Override
  public CharacterDescription create(ModelCreationContext context, Hero hero) {
    return new CharacterDescription();
  }
}
