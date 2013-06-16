package net.sf.anathema.character.main.traits.model;

import net.sf.anathema.character.main.model.initialization.SimpleModelTreeEntry;
import net.sf.anathema.character.main.model.CharacterModelAutoCollector;
import net.sf.anathema.character.main.model.CharacterModelFactory;
import net.sf.anathema.character.main.modeltemplate.TemplateFactory;

@CharacterModelAutoCollector
public class TraitModelFactory extends SimpleModelTreeEntry implements CharacterModelFactory {

  public TraitModelFactory() {
    super(TraitModel.ID);
  }

  @Override
  public DefaultTraitModel create(TemplateFactory templateFactory) {
    return new DefaultTraitModel();
  }
}
