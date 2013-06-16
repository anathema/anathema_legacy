package net.sf.anathema.character.main.traits.model;

import net.sf.anathema.character.main.experience.model.ExperienceModel;
import net.sf.anathema.character.main.model.initialization.SimpleModelTreeEntry;
import net.sf.anathema.character.model.CharacterModelAutoCollector;
import net.sf.anathema.character.model.CharacterModelFactory;
import net.sf.anathema.character.model.TemplateFactory;

@CharacterModelAutoCollector
public class TraitModelFactory extends SimpleModelTreeEntry implements CharacterModelFactory {

  public TraitModelFactory() {
    super(TraitModel.ID);
  }

  @Override
  public TraitModel create(TemplateFactory templateFactory) {
    return new DefaultTraitModel();
  }
}
