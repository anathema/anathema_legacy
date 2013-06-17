package net.sf.anathema.hero.attributes.model;

import net.sf.anathema.character.main.model.attributes.AttributeModel;
import net.sf.anathema.character.main.hero.CharacterModelAutoCollector;
import net.sf.anathema.character.main.hero.HeroModelFactory;
import net.sf.anathema.character.main.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.character.main.hero.template.TemplateFactory;
import net.sf.anathema.character.main.model.othertraits.OtherTraitModel;
import net.sf.anathema.character.main.model.traits.TraitModel;

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
