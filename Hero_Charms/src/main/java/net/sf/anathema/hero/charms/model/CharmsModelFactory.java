package net.sf.anathema.hero.charms.model;

import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.hero.charms.CharmsModel;
import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.essencepool.EssencePoolModel;
import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.health.HealthModel;
import net.sf.anathema.hero.magic.model.MagicModel;
import net.sf.anathema.hero.othertraits.OtherTraitModel;
import net.sf.anathema.hero.traits.TraitModel;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.template.TemplateFactory;

@HeroModelAutoCollector
public class CharmsModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public CharmsModelFactory() {
    super(CharmsModel.ID, MagicModel.ID, EssencePoolModel.ID, AttributeModel.ID, AbilitiesModel.ID, OtherTraitModel.ID, TraitModel.ID,
            ExperienceModel.ID, HeroConcept.ID, HealthModel.ID);
  }

  @Override
  public CharmsModel create(TemplateFactory templateFactory, String templateId) {
    return new CharmsModelImpl();
  }
}
