package net.sf.anathema.character.main.traits.model;

import net.sf.anathema.character.main.experience.model.ExperienceModel;
import net.sf.anathema.character.main.model.CharacterModelAutoCollector;
import net.sf.anathema.character.main.model.HeroModelFactory;
import net.sf.anathema.character.main.model.initialization.SimpleModelTreeEntry;
import net.sf.anathema.character.main.model.template.TemplateFactory;

@CharacterModelAutoCollector
public class TraitModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public TraitModelFactory() {
    super(TraitModel.ID, ExperienceModel.ID);
  }

  @Override
  public TraitModelImpl create(TemplateFactory templateFactory) {
    return new TraitModelImpl();
  }
}
