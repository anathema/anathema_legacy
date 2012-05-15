package net.sf.anathema.character.lunar.beastform.presenter;

import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;

public interface IBeastformAttribute {

  int getPointCost();

  IDefaultTrait getTrait();

  void recalculate();

  int getAdditionalDots();

}
