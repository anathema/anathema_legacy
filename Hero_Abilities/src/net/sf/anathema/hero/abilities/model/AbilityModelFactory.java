package net.sf.anathema.hero.abilities.model;

import net.sf.anathema.character.main.model.abilities.AbilityModel;
import net.sf.anathema.character.main.hero.CharacterModelAutoCollector;
import net.sf.anathema.character.main.hero.HeroModelFactory;
import net.sf.anathema.character.main.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.character.main.hero.template.TemplateFactory;
import net.sf.anathema.character.main.model.othertraits.OtherTraitModel;
import net.sf.anathema.character.main.model.traits.TraitModel;

@CharacterModelAutoCollector
public class AbilityModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public AbilityModelFactory() {
    super(AbilityModel.ID, OtherTraitModel.ID, TraitModel.ID);
  }

  @Override
  public AbilityModelImpl create(TemplateFactory templateFactory) {
    return new AbilityModelImpl();
  }
}
