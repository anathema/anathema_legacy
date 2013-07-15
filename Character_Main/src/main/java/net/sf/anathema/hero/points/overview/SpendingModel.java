package net.sf.anathema.hero.points.overview;

public interface SpendingModel extends IValueModel<Integer> {

  int getSpentBonusPoints();

  int getAllotment();
}