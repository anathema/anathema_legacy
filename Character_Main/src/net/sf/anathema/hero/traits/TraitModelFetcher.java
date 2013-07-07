package net.sf.anathema.hero.traits;

import net.sf.anathema.hero.model.Hero;

public class TraitModelFetcher {

  public static TraitModel fetch(Hero hero) {
    return (TraitModel) hero.getModel(TraitModel.ID);
  }
}
