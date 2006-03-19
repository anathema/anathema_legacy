package net.sf.anathema.character.lunar.beastform.presenter;

import net.sf.anathema.character.library.trait.ITrait;

public interface IBeastformAttribute {

  public int getPointCost();

  public ITrait getTrait();

  public void recalculate();

  public int getAdditionalDots();

}
