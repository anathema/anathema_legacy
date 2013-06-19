package net.sf.anathema.character.main.model.othertraits;

import net.sf.anathema.hero.model.Hero;

public class OtherTraitModelFetcher {

  public static OtherTraitModel fetch(Hero hero) {
    return (OtherTraitModel) hero.getModel(OtherTraitModel.ID);
  }
}
