package net.sf.anathema.hero.languages.model;

import net.sf.anathema.character.main.model.abilities.AbilitiesModel;
import net.sf.anathema.character.main.model.concept.HeroConcept;
import net.sf.anathema.character.main.model.experience.ExperienceModel;
import net.sf.anathema.character.main.model.othertraits.OtherTraitModel;
import net.sf.anathema.character.main.model.traits.TraitModel;
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
