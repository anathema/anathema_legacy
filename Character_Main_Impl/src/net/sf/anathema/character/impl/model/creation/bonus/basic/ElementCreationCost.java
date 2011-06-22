package net.sf.anathema.character.impl.model.creation.bonus.basic;

import net.sf.anathema.lib.lang.ReflectionEqualsObject;

public class ElementCreationCost extends ReflectionEqualsObject {

  private final int bonusPointsSpent;
  private final int extraFavoredDotsSpent;
  private final int extraGenericDotsSpent;
  private final int dotsSpent;

  public ElementCreationCost(int dotsSpent, int bonusPointsSpent)
  {
	  this(dotsSpent, 0, 0, bonusPointsSpent);
  }
  
  public ElementCreationCost(int dotsSpent, int extraFavoredDotsSpent, int extraGenericDotsSpent, int bonusPointsSpent) {
    this.bonusPointsSpent = bonusPointsSpent;
    this.extraFavoredDotsSpent = extraFavoredDotsSpent;
    this.extraGenericDotsSpent = extraGenericDotsSpent;
    this.dotsSpent = dotsSpent;
  }

  public int getBonusPointsSpent() {
    return bonusPointsSpent;
  }
  
  public int getExtraFavoredDotsSpent()
  {
	  return extraFavoredDotsSpent;
  }
  
  public int getExtraGenericDotsSpent()
  {
	  return extraGenericDotsSpent;
  }

  public int getDotsSpent() {
    return dotsSpent;
  }

  @Override
  public String toString() {
    return getClass().getName() + "{" + dotsSpent + "," + extraFavoredDotsSpent + "," +
    	extraGenericDotsSpent + "," + bonusPointsSpent + "}"; //$NON-NLS-1$ //$NON-NLS-2$ 
  }
}