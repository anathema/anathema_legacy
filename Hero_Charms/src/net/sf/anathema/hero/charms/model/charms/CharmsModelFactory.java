package net.sf.anathema.hero.charms.model.charms;

import net.sf.anathema.character.main.model.abilities.AbilitiesModel;
import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.character.main.model.charms.CharmsModel;
import net.sf.anathema.character.main.model.concept.HeroConcept;
import net.sf.anathema.character.main.model.essencepool.EssencePoolModel;
import net.sf.anathema.character.main.model.experience.ExperienceModel;
import net.sf.anathema.character.main.model.health.HealthModel;
import net.sf.anathema.character.main.model.othertraits.OtherTraitModel;
import net.sf.anathema.character.main.model.traits.TraitModel;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.template.TemplateFactory;

@HeroModelAutoCollector
public class CharmsModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public CharmsModelFactory() {
    super(CharmsModel.ID, EssencePoolModel.ID, AttributeModel.ID, AbilitiesModel.ID, OtherTraitModel.ID, TraitModel.ID, ExperienceModel.ID, HeroConcept.ID, HealthModel.ID);
  }

  @Override
  public CharmsModel create(TemplateFactory templateFactory) {
    return new CharmsModelImpl();
  }
}
