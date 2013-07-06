package net.sf.anathema.hero.intimacies.model;

import net.sf.anathema.character.main.model.concept.HeroConcept;
import net.sf.anathema.character.main.model.experience.ExperienceModel;
import net.sf.anathema.character.main.model.othertraits.OtherTraitModel;
import net.sf.anathema.character.main.model.traits.TraitModel;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.points.PointsModel;
import net.sf.anathema.hero.template.TemplateFactory;

@HeroModelAutoCollector
public class IntimaciesModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public IntimaciesModelFactory() {
    super(IntimaciesModel.ID, OtherTraitModel.ID, TraitModel.ID, ExperienceModel.ID, HeroConcept.ID, PointsModel.ID);
  }

  @Override
  public IntimaciesModel create(TemplateFactory templateFactory, String templateId) {
    return new IntimaciesModelImpl();
  }
}
