package net.sf.anathema.hero.spiritual.model.traits;

import net.sf.anathema.hero.spiritual.SpiritualTraitModel;
import net.sf.anathema.hero.spiritual.template.SpiritualTraitsTemplate;
import net.sf.anathema.hero.spiritual.template.SpiritualTraitsTemplateLoader;
import net.sf.anathema.hero.traits.TraitModel;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.template.TemplateFactory;

@HeroModelAutoCollector
public class SpiritualTraitModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public SpiritualTraitModelFactory() {
    super(SpiritualTraitModel.ID, TraitModel.ID);
  }

  @Override
  public SpiritualTraitModelImpl create(TemplateFactory templateFactory, String templateId) {
    SpiritualTraitsTemplate spiritualTraitsTemplate = SpiritualTraitsTemplateLoader.loadTemplate(templateFactory, templateId);
    return new SpiritualTraitModelImpl(spiritualTraitsTemplate);
  }
}
