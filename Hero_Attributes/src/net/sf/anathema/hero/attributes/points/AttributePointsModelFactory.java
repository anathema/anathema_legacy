package net.sf.anathema.hero.attributes.points;

import net.sf.anathema.character.main.model.attributes.AttributeModel;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.points.PointsModel;
import net.sf.anathema.hero.template.TemplateFactory;

@HeroModelAutoCollector
public class AttributePointsModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public AttributePointsModelFactory() {
    super(AttributePointsModelImpl.ID, AttributeModel.ID, PointsModel.ID);
  }

  @Override
  public AttributePointsModelImpl create(TemplateFactory templateFactory) {
    return new AttributePointsModelImpl();
  }
}
