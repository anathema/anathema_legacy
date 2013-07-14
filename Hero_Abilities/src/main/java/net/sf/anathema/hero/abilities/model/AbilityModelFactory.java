package net.sf.anathema.hero.abilities.model;

import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.spiritual.SpiritualTraitModel;
import net.sf.anathema.hero.template.TemplateFactory;
import net.sf.anathema.hero.traits.TraitModel;

@HeroModelAutoCollector
public class AbilityModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public AbilityModelFactory() {
    super(AbilitiesModel.ID, SpiritualTraitModel.ID, TraitModel.ID, HeroConcept.ID, ExperienceModel.ID);
  }

  @Override
  public AbilitiesModelImpl create(TemplateFactory templateFactory, String templateId) {
    return new AbilitiesModelImpl();
  }
}
