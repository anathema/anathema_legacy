package net.sf.anathema.hero.traits.model;

import net.sf.anathema.character.main.model.experience.ExperienceModel;
import net.sf.anathema.character.main.model.traits.TraitModel;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.template.TemplateFactory;

@HeroModelAutoCollector
public class TraitModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public TraitModelFactory() {
    super(TraitModel.ID, ExperienceModel.ID);
  }

  @Override
  public TraitModelImpl create(TemplateFactory templateFactory, String templateId) {
    return new TraitModelImpl();
  }
}
