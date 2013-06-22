package net.sf.anathema.character.main.model.attributes;

import net.sf.anathema.hero.model.Hero;

public class AttributesModelFetcher {

  public static AttributeModel fetch(Hero hero) {
    return (AttributeModel) hero.getModel(AttributeModel.ID);
  }
}
