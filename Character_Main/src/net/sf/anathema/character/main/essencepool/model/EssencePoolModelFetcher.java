package net.sf.anathema.character.main.essencepool.model;

import net.sf.anathema.character.main.model.Hero;

public class EssencePoolModelFetcher {

  public static EssencePoolModel fetch(Hero hero) {
    return (EssencePoolModel) hero.getModel(EssencePoolModel.ID);
  }
}
