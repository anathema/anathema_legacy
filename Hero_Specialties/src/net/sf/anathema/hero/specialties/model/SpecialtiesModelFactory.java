package net.sf.anathema.hero.specialties.model;

import net.sf.anathema.character.library.trait.specialties.SpecialtiesModel;
import net.sf.anathema.character.main.model.abilities.AbilitiesModel;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.template.TemplateFactory;

@HeroModelAutoCollector
public class SpecialtiesModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public SpecialtiesModelFactory() {
    super(SpecialtiesModel.ID, AbilitiesModel.ID);
  }

  @Override
  public SpecialtiesModel create(TemplateFactory templateFactory, String templateId) {
    return new SpecialtiesModelImpl();
  }
}
