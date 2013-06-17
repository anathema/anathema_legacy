package net.sf.anathema.hero.othertraits.model.pool;

import net.sf.anathema.character.main.model.essencepool.EssencePoolModel;
import net.sf.anathema.character.main.hero.CharacterModelAutoCollector;
import net.sf.anathema.character.main.hero.HeroModelFactory;
import net.sf.anathema.character.main.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.character.main.hero.template.TemplateFactory;

@CharacterModelAutoCollector
public class ExperiencePoolModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public ExperiencePoolModelFactory() {
    super(EssencePoolModel.ID);
  }

  @Override
  public EssencePoolModelImpl create(TemplateFactory templateFactory) {
    return new EssencePoolModelImpl();
  }
}
