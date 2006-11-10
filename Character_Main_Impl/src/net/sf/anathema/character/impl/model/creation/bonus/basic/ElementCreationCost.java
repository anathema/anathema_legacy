package net.sf.anathema.character.impl.model.creation.bonus.basic;

import net.sf.anathema.lib.lang.ReflectionEqualsObject;

public class ElementCreationCost extends ReflectionEqualsObject {

  private final int bonusPointsSpent;
  private final int dotsSpent;

  public ElementCreationCost(int dotsSpent, int bonusPointsSpent) {
    this.bonusPointsSpent = bonusPointsSpent;
    this.dotsSpent = dotsSpent;
  }

  public int getBonusPointsSpent() {
    return bonusPointsSpent;
  }

  public int getDotsSpent() {
    return dotsSpent;
  }

  @Override
  public String toString() {
    return getClass().getName() + "{" + dotsSpent + "," + bonusPointsSpent + "}"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }
}