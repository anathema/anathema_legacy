package net.sf.anathema.character.impl.model.creation.bonus.basic;

import net.sf.anathema.lib.lang.ReflectionEqualsObject;

public class ElementCreationCost extends ReflectionEqualsObject {

  private final int bonusPointsSpent;
  private final int extraDotsSpent;
  private final int dotsSpent;

  public ElementCreationCost(int dotsSpent, int bonusPointsSpent)
  {
	  this(dotsSpent, 0, bonusPointsSpent);
  }
  
  public ElementCreationCost(int dotsSpent, int extraDotsSpent, int bonusPointsSpent) {
    this.bonusPointsSpent = bonusPointsSpent;
    this.extraDotsSpent = extraDotsSpent;
    this.dotsSpent = dotsSpent;
  }

  public int getBonusPointsSpent() {
    return bonusPointsSpent;
  }
  
  public int getExtraDotsSpent()
  {
	  return extraDotsSpent;
  }

  public int getDotsSpent() {
    return dotsSpent;
  }

  @Override
  public String toString() {
    return getClass().getName() + "{" + dotsSpent + "," + extraDotsSpent + "," + bonusPointsSpent + "}"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }
}