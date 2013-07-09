package net.sf.anathema.hero.spells.model;

import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.hero.charms.CharmsModel;
import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.health.HealthModel;
import net.sf.anathema.hero.othertraits.OtherTraitModel;
import net.sf.anathema.hero.spells.SpellModel;
import net.sf.anathema.hero.traits.TraitModel;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.template.TemplateFactory;

@HeroModelAutoCollector
public class SpellsModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public SpellsModelFactory() {
    super(SpellModel.ID, CharmsModel.ID, AttributeModel.ID, AbilitiesModel.ID, OtherTraitModel.ID, TraitModel.ID, ExperienceModel.ID, HeroConcept.ID,
            HealthModel.ID);
  }

  @Override
  public SpellModel create(TemplateFactory templateFactory, String templateId) {
    return new SpellModelImpl();
  }
}
