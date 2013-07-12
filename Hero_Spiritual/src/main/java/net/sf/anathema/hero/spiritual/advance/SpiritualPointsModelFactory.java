package net.sf.anathema.hero.spiritual.advance;

import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.points.PointsModel;
import net.sf.anathema.hero.spiritual.SpiritualTraitModel;
import net.sf.anathema.hero.template.TemplateFactory;

@HeroModelAutoCollector
public class SpiritualPointsModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public SpiritualPointsModelFactory() {
    super(SpiritualPointsModel.ID, SpiritualTraitModel.ID, PointsModel.ID);
  }

  @Override
  public SpiritualPointsModel create(TemplateFactory templateFactory, String templateId) {
    return new SpiritualPointsModel();
  }
}
