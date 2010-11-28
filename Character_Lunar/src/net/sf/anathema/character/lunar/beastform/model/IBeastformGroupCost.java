package net.sf.anathema.character.lunar.beastform.model;

public interface IBeastformGroupCost {

  public int getUnspentDots();

  public void addCostChangeListener(IAlotmentChangedListener listener);

  public int getTotalDots();

  public int getSpentDots();

}
