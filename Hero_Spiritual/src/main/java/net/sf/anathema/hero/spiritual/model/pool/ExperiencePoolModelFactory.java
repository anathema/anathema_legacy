package net.sf.anathema.hero.spiritual.model.pool;

import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.template.TemplateFactory;

@SuppressWarnings("UnusedDeclaration")
public class ExperiencePoolModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public ExperiencePoolModelFactory() {
    super(EssencePoolModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public EssencePoolModelImpl create(TemplateFactory templateFactory, String templateId) {
    return new EssencePoolModelImpl();
  }
}
