package net.sf.anathema.hero.othertraits.model.traits;

import net.sf.anathema.character.main.hero.CharacterModelAutoCollector;
import net.sf.anathema.character.main.hero.HeroModelFactory;
import net.sf.anathema.character.main.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.character.main.hero.template.TemplateFactory;
import net.sf.anathema.character.main.model.othertraits.OtherTraitModel;
import net.sf.anathema.character.main.model.traits.TraitModel;

@CharacterModelAutoCollector
public class OtherTraitModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public OtherTraitModelFactory() {
    super(OtherTraitModel.ID, TraitModel.ID);
  }

  @Override
  public OtherTraitModelImpl create(TemplateFactory templateFactory) {
    return new OtherTraitModelImpl();
  }
}
