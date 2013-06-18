package net.sf.anathema.character.main.model.othertraits;

import net.sf.anathema.character.main.hero.Hero;

public class OtherTraitModelFetcher {

  public static OtherTraitModel fetch(Hero hero) {
    return (OtherTraitModel) hero.getModel(OtherTraitModel.ID);
  }
}
