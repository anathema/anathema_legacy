package net.sf.anathema.character.lunar.beastform.model;

public interface IBeastformGroupCost {

  int getUnspentDots();

  void addCostChangeListener(IAlotmentChangedListener listener);

  int getTotalDots();

  int getSpentDots();

}
