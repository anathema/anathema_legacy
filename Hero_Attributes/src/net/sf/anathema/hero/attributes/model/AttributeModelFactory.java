package net.sf.anathema.hero.attributes.model;

import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.othertraits.OtherTraitModel;
import net.sf.anathema.hero.traits.TraitModel;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.template.TemplateFactory;

@HeroModelAutoCollector
public class AttributeModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public AttributeModelFactory() {
    super(AttributeModel.ID, OtherTraitModel.ID, TraitModel.ID, HeroConcept.ID, ExperienceModel.ID);
  }

  @Override
  public AttributeModelImpl create(TemplateFactory templateFactory, String templateId) {
    return new AttributeModelImpl();
  }
}
