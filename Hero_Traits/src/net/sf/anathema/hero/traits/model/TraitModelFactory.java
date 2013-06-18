package net.sf.anathema.hero.traits.model;

import net.sf.anathema.character.main.model.experience.ExperienceModel;
import net.sf.anathema.character.main.hero.CharacterModelAutoCollector;
import net.sf.anathema.character.main.hero.HeroModelFactory;
import net.sf.anathema.character.main.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.character.main.hero.template.TemplateFactory;
import net.sf.anathema.character.main.model.traits.TraitModel;

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
