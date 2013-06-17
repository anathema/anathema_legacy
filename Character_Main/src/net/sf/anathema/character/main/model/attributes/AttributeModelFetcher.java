package net.sf.anathema.character.main.model.attributes;

import net.sf.anathema.character.main.hero.Hero;

public class AttributeModelFetcher {

  public static AttributeModel fetch(Hero hero) {
    return (AttributeModel) hero.getModel(AttributeModel.ID);
  }
}
