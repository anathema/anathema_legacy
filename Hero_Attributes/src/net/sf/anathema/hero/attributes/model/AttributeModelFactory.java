package net.sf.anathema.hero.attributes.model;

import net.sf.anathema.character.main.model.concept.HeroConcept;
import net.sf.anathema.character.main.model.experience.ExperienceModel;
import net.sf.anathema.character.main.model.othertraits.OtherTraitModel;
import net.sf.anathema.character.main.model.traits.TraitModel;
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
  public AttributeModelImpl create(TemplateFactory templateFactory) {
    return new AttributeModelImpl();
  }
}
