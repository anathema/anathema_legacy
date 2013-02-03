package net.sf.anathema.character.presenter.overview;

public interface ISpendingModel extends IValueModel<Integer> {

  int getSpentBonusPoints();

  int getAlotment();
}