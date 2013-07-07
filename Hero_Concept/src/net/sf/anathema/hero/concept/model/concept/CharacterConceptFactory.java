package net.sf.anathema.hero.concept.model.concept;

import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.template.TemplateFactory;

@HeroModelAutoCollector
public class CharacterConceptFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public CharacterConceptFactory() {
    super(HeroConcept.ID, ExperienceModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public DefaultHeroConcept create(TemplateFactory templateFactory, String templateId) {
    return new DefaultHeroConcept();
  }
}
