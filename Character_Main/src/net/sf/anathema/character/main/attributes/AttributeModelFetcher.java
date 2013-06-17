package net.sf.anathema.character.main.attributes;

import net.sf.anathema.character.main.model.Hero;

public class AttributeModelFetcher {

  public static AttributeModel fetch(Hero hero) {
    return (AttributeModel) hero.getModel(AttributeModel.ID);
  }
}
