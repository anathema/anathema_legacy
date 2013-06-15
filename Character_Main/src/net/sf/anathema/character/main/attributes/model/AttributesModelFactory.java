package net.sf.anathema.character.main.attributes.model;

import net.sf.anathema.character.main.attributes.template.AttributeTemplate;
import net.sf.anathema.character.model.CharacterModelAutoCollector;
import net.sf.anathema.character.model.CharacterModelFactory;
import net.sf.anathema.character.model.TemplateFactory;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.lib.util.Identifier;

@CharacterModelAutoCollector
public class AttributesModelFactory implements CharacterModelFactory {

  @Override
  public Identified getModelId() {
    return AttributesModel.MODEL_ID;
  }

  @Override
  public AttributesModel create(TemplateFactory templateFactory) {
    Identifier templateId = new Identifier("attributeDefault");
    AttributeTemplate template = templateFactory.loadModelTemplate(templateId, new AttributeModelTemplateLoader());
    return new AttributesModel(template);
  }
}
