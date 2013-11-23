package net.sf.anathema.hero.intimacies.model;

import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.points.PointsModel;
import net.sf.anathema.hero.spiritual.SpiritualTraitModel;
import net.sf.anathema.hero.template.TemplateFactory;
import net.sf.anathema.hero.traits.TraitModel;

@SuppressWarnings("UnusedDeclaration")
public class IntimaciesModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public IntimaciesModelFactory() {
    super(IntimaciesModel.ID, SpiritualTraitModel.ID, TraitModel.ID, ExperienceModel.ID, HeroConcept.ID, PointsModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public IntimaciesModel create(TemplateFactory templateFactory, String templateId) {
    return new IntimaciesModelImpl();
  }
}
