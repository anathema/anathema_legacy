package net.sf.anathema.hero.othertraits.model.pool;

import net.sf.anathema.hero.model.Hero;

public class EssencePoolModelFetcher {

  public static EssencePoolModel fetch(Hero hero) {
    return (EssencePoolModel) hero.getModel(EssencePoolModel.ID);
  }
}
