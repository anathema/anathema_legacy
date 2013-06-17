package net.sf.anathema.character.main.othertraits;

import net.sf.anathema.character.main.model.CharacterModelAutoCollector;
import net.sf.anathema.character.main.model.CharacterModelFactory;
import net.sf.anathema.character.main.model.initialization.SimpleModelTreeEntry;
import net.sf.anathema.character.main.model.template.TemplateFactory;
import net.sf.anathema.character.main.traits.model.TraitModel;

@CharacterModelAutoCollector
public class OtherTraitModelFactory extends SimpleModelTreeEntry implements CharacterModelFactory {

  public OtherTraitModelFactory() {
    super(OtherTraitModel.ID, TraitModel.ID);
  }

  @Override
  public OtherTraitModelImpl create(TemplateFactory templateFactory) {
    return new OtherTraitModelImpl();
  }
}
