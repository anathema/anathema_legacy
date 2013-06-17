package net.sf.anathema.character.main.essencepool.model;

import net.sf.anathema.character.main.model.CharacterModelAutoCollector;
import net.sf.anathema.character.main.model.HeroModelFactory;
import net.sf.anathema.character.main.model.initialization.SimpleModelTreeEntry;
import net.sf.anathema.character.main.model.template.TemplateFactory;

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
