package net.sf.anathema.hero.othertraits.model;

import net.sf.anathema.character.main.model.CharacterModelAutoCollector;
import net.sf.anathema.character.main.model.HeroModelFactory;
import net.sf.anathema.character.main.model.initialization.SimpleModelTreeEntry;
import net.sf.anathema.character.main.model.template.TemplateFactory;
import net.sf.anathema.character.main.othertraits.OtherTraitModel;
import net.sf.anathema.character.main.traits.model.TraitModel;

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
