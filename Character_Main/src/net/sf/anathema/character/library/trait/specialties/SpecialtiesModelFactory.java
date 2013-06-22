package net.sf.anathema.character.library.trait.specialties;

import net.sf.anathema.character.main.model.abilities.AbilityModel;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.template.TemplateFactory;

@HeroModelAutoCollector
public class SpecialtiesModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public SpecialtiesModelFactory() {
    super(SpecialtiesModel.ID, AbilityModel.ID);
  }

  @Override
  public SpecialtiesModel create(TemplateFactory templateFactory) {
    return new SpecialtiesModelImpl();
  }
}
