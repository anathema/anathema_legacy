package net.sf.anathema.hero.spiritual.model.pool;

import net.sf.anathema.hero.model.Hero;

public class EssencePoolModelFetcher {

  public static EssencePoolModel fetch(Hero hero) {
    return (EssencePoolModel) hero.getModel(EssencePoolModel.ID);
  }
}
