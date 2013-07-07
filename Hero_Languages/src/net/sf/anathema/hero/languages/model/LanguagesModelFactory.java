package net.sf.anathema.hero.languages.model;

import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.othertraits.OtherTraitModel;
import net.sf.anathema.hero.traits.TraitModel;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.template.TemplateFactory;

@HeroModelAutoCollector
public class LanguagesModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public LanguagesModelFactory() {
    super(LanguagesModel.ID, AbilitiesModel.ID, OtherTraitModel.ID, TraitModel.ID, ExperienceModel.ID, HeroConcept.ID);
  }

  @Override
  public LanguagesModel create(TemplateFactory templateFactory, String templateId) {
    return new LanguagesModelImpl();
  }
}
