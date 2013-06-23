package net.sf.anathema.hero.equipment;

import net.sf.anathema.character.library.trait.specialties.SpecialtiesModel;
import net.sf.anathema.character.main.model.abilities.AbilitiesModel;
import net.sf.anathema.character.reporting.pdf.model.StatsModel;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.template.TemplateFactory;

@HeroModelAutoCollector
public class EquipmentModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public EquipmentModelFactory() {
    super(EquipmentModel.ID, AbilitiesModel.ID, SpecialtiesModel.ID, StatsModel.ID);
  }

  @Override
  public EquipmentModel create(TemplateFactory templateFactory) {
    return new EquipmentModelImpl();
  }
}
