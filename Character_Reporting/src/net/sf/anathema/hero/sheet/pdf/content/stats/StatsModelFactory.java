package net.sf.anathema.hero.sheet.pdf.content.stats;

import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.template.TemplateFactory;

@HeroModelAutoCollector
public class StatsModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public StatsModelFactory() {
    super(StatsModel.ID);
  }

  @Override
  public StatsModelImpl create(TemplateFactory templateFactory, String templateId) {
    return new StatsModelImpl();
  }
}
