package net.sf.anathema.hero.abilities.model;

import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.character.main.model.abilities.AbilityModel;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.template.TemplateFactory;
import net.sf.anathema.character.main.model.othertraits.OtherTraitModel;
import net.sf.anathema.character.main.model.traits.TraitModel;

@HeroModelAutoCollector
public class AbilityModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public AbilityModelFactory() {
    super(AbilityModel.ID, OtherTraitModel.ID, TraitModel.ID);
  }

  @Override
  public AbilityModelImpl create(TemplateFactory templateFactory) {
    return new AbilityModelImpl();
  }
}
