package net.sf.anathema.character.main.model.combos;

import net.sf.anathema.character.main.hero.CharacterModelAutoCollector;
import net.sf.anathema.character.main.hero.HeroModelFactory;
import net.sf.anathema.character.main.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.character.main.hero.template.TemplateFactory;
import net.sf.anathema.character.main.model.abilities.AbilityModel;
import net.sf.anathema.character.main.model.attributes.AttributeModel;
import net.sf.anathema.character.main.model.charms.CharmsModel;
import net.sf.anathema.character.main.model.concept.HeroConcept;
import net.sf.anathema.character.main.model.experience.ExperienceModel;
import net.sf.anathema.character.main.model.health.HealthModel;
import net.sf.anathema.character.main.model.othertraits.OtherTraitModel;
import net.sf.anathema.character.main.model.traits.TraitModel;

@CharacterModelAutoCollector
public class CombosModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public CombosModelFactory() {
    super(CombosModel.ID, CharmsModel.ID, AttributeModel.ID, AbilityModel.ID, OtherTraitModel.ID, TraitModel.ID, ExperienceModel.ID, HeroConcept.ID,
            HealthModel.ID);
  }

  @Override
  public CombosModel create(TemplateFactory templateFactory) {
    return new CombosModelImpl();
  }
}
