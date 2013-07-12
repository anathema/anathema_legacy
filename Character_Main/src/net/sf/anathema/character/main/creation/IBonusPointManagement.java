package net.sf.anathema.character.main.creation;

import net.sf.anathema.hero.points.overview.IOverviewModel;
import net.sf.anathema.hero.points.overview.ISpendingModel;

public interface IBonusPointManagement {

  void recalculate();

  ISpendingModel getTotalModel();

  IOverviewModel[] getAllModels();
}