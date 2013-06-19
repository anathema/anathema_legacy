package net.sf.anathema.hero.othertraits.model.pool;

import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.character.main.model.essencepool.EssencePoolModel;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.template.TemplateFactory;

@HeroModelAutoCollector
public class ExperiencePoolModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public ExperiencePoolModelFactory() {
    super(EssencePoolModel.ID);
  }

  @Override
  public EssencePoolModelImpl create(TemplateFactory templateFactory) {
    return new EssencePoolModelImpl();
  }
}
