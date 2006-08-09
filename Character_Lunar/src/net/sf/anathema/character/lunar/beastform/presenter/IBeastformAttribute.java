package net.sf.anathema.character.lunar.beastform.presenter;

import net.sf.anathema.character.library.trait.IDefaultTrait;

public interface IBeastformAttribute {

  public int getPointCost();

  public IDefaultTrait getTrait();

  public void recalculate();

  public int getAdditionalDots();

}
