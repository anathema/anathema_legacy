package net.sf.anathema.character.main.model.charms;

import net.sf.anathema.hero.model.Hero;

public class CharmsModelFetcher {

  public static CharmsModel fetch(Hero hero) {
    return hero.getModel(CharmsModel.ID);
  }
}
