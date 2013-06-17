package net.sf.anathema.character.main.model.traits;

import net.sf.anathema.character.main.hero.Hero;

public class TraitModelFetcher {

  public static TraitModel fetch(Hero hero) {
    return (TraitModel) hero.getModel(TraitModel.ID);
  }
}
