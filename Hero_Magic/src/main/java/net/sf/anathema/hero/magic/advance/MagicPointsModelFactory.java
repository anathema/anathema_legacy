package net.sf.anathema.hero.magic.advance;

import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.magic.model.MagicModel;
import net.sf.anathema.hero.magic.template.advance.MagicPointsTemplate;
import net.sf.anathema.hero.magic.template.advance.MagicPointsTemplateLoader;
import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.points.PointsModel;
import net.sf.anathema.hero.template.TemplateFactory;

@HeroModelAutoCollector
public class MagicPointsModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public MagicPointsModelFactory() {
    super(MagicPointsModel.ID, MagicModel.ID, PointsModel.ID);
  }

  @Override
  public MagicPointsModel create(TemplateFactory templateFactory, String templateId) {
    MagicPointsTemplate template = MagicPointsTemplateLoader.loadTemplate(templateFactory, templateId);
    return new MagicPointsModel(template);
  }
}
