package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.attributes.template.AttributeTemplate;
import net.sf.anathema.character.model.CharacterModelAutoCollector;
import net.sf.anathema.character.model.CharacterModelFactory;
import net.sf.anathema.character.model.Character;
import net.sf.anathema.character.model.CharacterModelGroup;
import net.sf.anathema.character.model.ModelCreationContext;
import net.sf.anathema.character.model.ModelGroup;
import net.sf.anathema.initialization.reflections.Weight;

@CharacterModelAutoCollector
@Weight(weight = 0)
@ModelGroup(group = CharacterModelGroup.NaturalTraits)
public class AttributesModelFactory implements CharacterModelFactory {

  @Override
  public AttributesModel create(ModelCreationContext context, Character character) {
    AttributeTemplate template = null;
    return new AttributesModel(template);
  }
}
