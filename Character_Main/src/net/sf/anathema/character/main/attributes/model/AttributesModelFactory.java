package net.sf.anathema.character.main.attributes.model;

import net.sf.anathema.character.main.attributes.template.AttributeTemplate;
import net.sf.anathema.character.main.experience.model.ExperienceModel;
import net.sf.anathema.character.main.model.initialization.SimpleModelTreeEntry;
import net.sf.anathema.character.main.model.CharacterModelAutoCollector;
import net.sf.anathema.character.main.model.CharacterModelFactory;
import net.sf.anathema.character.main.modeltemplate.TemplateFactory;
import net.sf.anathema.lib.util.SimpleIdentifier;

@CharacterModelAutoCollector
public class AttributesModelFactory extends SimpleModelTreeEntry implements CharacterModelFactory {

  public AttributesModelFactory() {
    super(AttributesModel.MODEL_ID, ExperienceModel.ID);
  }

  @Override
  public AttributesModel create(TemplateFactory templateFactory) {
    SimpleIdentifier templateId = new SimpleIdentifier("attributeDefault");
    AttributeTemplate template = templateFactory.loadModelTemplate(templateId, new AttributeModelTemplateLoader());
    return new AttributesModel(template);
  }
}
