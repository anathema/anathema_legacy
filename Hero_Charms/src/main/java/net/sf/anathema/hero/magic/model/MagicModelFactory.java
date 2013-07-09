package net.sf.anathema.hero.magic.model;

import net.sf.anathema.hero.charms.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelImpl;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.template.TemplateFactory;

@HeroModelAutoCollector
public class MagicModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public MagicModelFactory() {
    super(MagicModel.ID);
  }

  @Override
  public MagicModel create(TemplateFactory templateFactory, String templateId) {
    return new MagicModelImpl();
  }
}
