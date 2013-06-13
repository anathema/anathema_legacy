package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.attributes.template.AttributeTemplate;
import net.sf.anathema.character.model.CharacterModelAutoCollector;
import net.sf.anathema.character.model.CharacterModelFactory;
import net.sf.anathema.character.model.CharacterModelGroup;
import net.sf.anathema.character.model.Hero;
import net.sf.anathema.character.model.ModelCreationContext;
import net.sf.anathema.character.model.ModelGroup;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.util.Identifier;

@CharacterModelAutoCollector
@Weight(weight = 0)
@ModelGroup(group = CharacterModelGroup.NaturalTraits)
public class AttributesModelFactory implements CharacterModelFactory {

  @Override
  public AttributesModel create(ModelCreationContext context, Hero hero) {
    Identifier templateId = new Identifier("attributeDefault");
    AttributeTemplate template = context.loadModelTemplate(templateId, new AttributeModelTemplateLoader());
    return new AttributesModel(template);
  }
}
