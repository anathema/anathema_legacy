package net.sf.anathema.hero.advance.creation;

import net.sf.anathema.hero.points.overview.IOverviewModel;
import net.sf.anathema.hero.points.overview.ISpendingModel;

public interface IBonusPointManagement {

  void recalculate();

  ISpendingModel getTotalModel();

  IOverviewModel[] getAllModels();
}