package net.sf.anathema.hero.advance.creation;

import net.sf.anathema.hero.points.overview.SpendingModel;

public interface IBonusPointManagement {

  void recalculate();

  String getSummaryCategory();

  SpendingModel getTotalModel();
}