package net.sf.anathema.character.main.model.essencepool;

import net.sf.anathema.hero.model.Hero;

public class EssencePoolModelFetcher {

  public static EssencePoolModel fetch(Hero hero) {
    return (EssencePoolModel) hero.getModel(EssencePoolModel.ID);
  }
}
