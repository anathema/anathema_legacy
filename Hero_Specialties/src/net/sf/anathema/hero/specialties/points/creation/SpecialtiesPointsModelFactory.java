package net.sf.anathema.hero.specialties.points.creation;

import net.sf.anathema.character.main.model.abilities.AbilitiesModel;
import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.points.PointsModel;
import net.sf.anathema.hero.template.TemplateFactory;

@HeroModelAutoCollector
public class SpecialtiesPointsModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public SpecialtiesPointsModelFactory() {
    super(SpecialtiesPointsModel.ID, AbilitiesModel.ID, AttributeModel.ID, PointsModel.ID);
  }

  @Override
  public SpecialtiesPointsModel create(TemplateFactory templateFactory) {
    return new SpecialtiesPointsModel();
  }
}
