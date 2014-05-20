package net.sf.anathema.hero.spiritual.model.pool;

import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.spiritual.template.EssencePoolTemplate;
import net.sf.anathema.hero.spiritual.template.EssencePoolTemplateLoader;
import net.sf.anathema.hero.template.TemplateFactory;

@SuppressWarnings("UnusedDeclaration")
public class ExperiencePoolModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public ExperiencePoolModelFactory() {
    super(EssencePoolModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public EssencePoolModelImpl create(TemplateFactory templateFactory, String templateId) {
    EssencePoolTemplate template = EssencePoolTemplateLoader.loadTemplate(templateFactory, templateId);
    return new EssencePoolModelImpl(template);
  }
}
