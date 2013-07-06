package net.sf.anathema.hero.points.overview;

public interface ISpendingModel extends IValueModel<Integer> {

  int getSpentBonusPoints();

  int getAlotment();
}