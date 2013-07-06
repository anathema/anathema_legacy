package net.sf.anathema.hero.points;

import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.template.TemplateFactory;

@HeroModelAutoCollector
public class PointModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public PointModelFactory() {
    super(PointsModel.ID);
  }

  @Override
  public PointsModel create(TemplateFactory templateFactory, String templateId) {
    return new PointModelImpl();
  }
}
