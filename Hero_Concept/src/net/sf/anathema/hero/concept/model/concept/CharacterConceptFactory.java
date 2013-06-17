package net.sf.anathema.hero.concept.model.concept;

import net.sf.anathema.character.main.hero.CharacterModelAutoCollector;
import net.sf.anathema.character.main.hero.HeroModelFactory;
import net.sf.anathema.character.main.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.character.main.hero.template.TemplateFactory;
import net.sf.anathema.character.main.model.concept.CharacterConcept;
import net.sf.anathema.character.main.model.experience.ExperienceModel;

@CharacterModelAutoCollector
public class CharacterConceptFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public CharacterConceptFactory() {
    super(CharacterConcept.ID, ExperienceModel.ID);
  }

  @Override
  public CharacterConceptImpl create(TemplateFactory templateFactory) {
    return new CharacterConceptImpl();
  }
}
