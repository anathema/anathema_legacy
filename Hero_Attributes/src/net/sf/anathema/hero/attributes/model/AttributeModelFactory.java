package net.sf.anathema.hero.attributes.model;

import net.sf.anathema.character.main.attributes.AttributeModel;
import net.sf.anathema.character.main.model.CharacterModelAutoCollector;
import net.sf.anathema.character.main.model.HeroModelFactory;
import net.sf.anathema.character.main.model.initialization.SimpleModelTreeEntry;
import net.sf.anathema.character.main.model.template.TemplateFactory;
import net.sf.anathema.character.main.othertraits.OtherTraitModel;
import net.sf.anathema.character.main.traits.model.TraitModel;

@CharacterModelAutoCollector
public class AttributeModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public AttributeModelFactory() {
    super(AttributeModel.ID, OtherTraitModel.ID, TraitModel.ID);
  }

  @Override
  public AttributeModelImpl create(TemplateFactory templateFactory) {
    return new AttributeModelImpl();
  }
}
