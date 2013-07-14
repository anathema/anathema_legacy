package net.sf.anathema.hero.attributes.model;

import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.spiritual.SpiritualTraitModel;
import net.sf.anathema.hero.template.TemplateFactory;
import net.sf.anathema.hero.traits.TraitModel;

@HeroModelAutoCollector
public class AttributeModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public AttributeModelFactory() {
    super(AttributeModel.ID, SpiritualTraitModel.ID, TraitModel.ID, HeroConcept.ID, ExperienceModel.ID);
  }

  @Override
  public AttributeModelImpl create(TemplateFactory templateFactory, String templateId) {
    return new AttributeModelImpl();
  }
}
