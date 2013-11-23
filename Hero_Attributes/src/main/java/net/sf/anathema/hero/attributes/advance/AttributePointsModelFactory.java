package net.sf.anathema.hero.attributes.advance;

import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.points.PointsModel;
import net.sf.anathema.hero.template.TemplateFactory;

@SuppressWarnings("UnusedDeclaration")
public class AttributePointsModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public AttributePointsModelFactory() {
    super(AttributePointsModel.ID, AttributeModel.ID, PointsModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public AttributePointsModel create(TemplateFactory templateFactory, String templateId) {
    return new AttributePointsModel();
  }
}
