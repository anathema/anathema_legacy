package net.sf.anathema.character.main.traits.model;

import net.sf.anathema.character.main.model.Hero;

public class TraitModelFetcher {

  public static TraitModel fetch(Hero hero) {
    return (TraitModel) hero.getModel(TraitModel.ID);
  }
}
