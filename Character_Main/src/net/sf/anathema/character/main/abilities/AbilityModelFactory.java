package net.sf.anathema.character.main.abilities;

import net.sf.anathema.character.main.model.CharacterModelAutoCollector;
import net.sf.anathema.character.main.model.HeroModelFactory;
import net.sf.anathema.character.main.model.initialization.SimpleModelTreeEntry;
import net.sf.anathema.character.main.model.template.TemplateFactory;
import net.sf.anathema.character.main.othertraits.OtherTraitModel;
import net.sf.anathema.character.main.traits.model.TraitModel;

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
