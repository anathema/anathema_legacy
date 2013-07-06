package net.sf.anathema.hero.othertraits.model.traits;

import net.sf.anathema.character.main.model.othertraits.OtherTraitModel;
import net.sf.anathema.character.main.model.traits.TraitModel;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.othertraits.template.OtherTraitsTemplate;
import net.sf.anathema.hero.othertraits.template.OtherTraitsTemplateLoader;
import net.sf.anathema.hero.template.TemplateFactory;

@HeroModelAutoCollector
public class OtherTraitModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public OtherTraitModelFactory() {
    super(OtherTraitModel.ID, TraitModel.ID);
  }

  @Override
  public OtherTraitModelImpl create(TemplateFactory templateFactory, String templateId) {
    OtherTraitsTemplate otherTraitsTemplate = OtherTraitsTemplateLoader.loadTemplate(templateFactory, templateId);
    return new OtherTraitModelImpl(otherTraitsTemplate);
  }
}
