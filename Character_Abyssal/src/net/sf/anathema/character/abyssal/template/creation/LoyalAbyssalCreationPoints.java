package net.sf.anathema.character.abyssal.template.creation;

import net.sf.anathema.character.generic.impl.template.points.AbilityCreationPoints;
import net.sf.anathema.character.generic.impl.template.points.AbstractCreationPoints;
import net.sf.anathema.character.generic.impl.template.points.AttributeCreationPoints;
import net.sf.anathema.character.generic.template.points.IAbilityCreationPoints;
import net.sf.anathema.character.generic.template.points.IAttributeCreationPoints;

public class LoyalAbyssalCreationPoints extends AbstractCreationPoints {

  public int getBackgroundPointCount() {
    return 13;
  }

  public final IAttributeCreationPoints getAttributeCreationPoints() {
    return new AttributeCreationPoints(8, 6, 4);
  }

  public final int getBonusPointCount() {
    return 15;
  }

  public int getSpecialtyCreationPoints() {
    return 0;
  }
  
  public final IAbilityCreationPoints getAbilityCreationPoints() {
    return new AbilityCreationPoints(5, 10, 15);
  }

  public final int getDefaultCreationCharmCount() {
    return 5;
  }

  public final int getFavoredCreationCharmCount() {
    return 5;
  }
  
  public int getUniqueRequiredCreationCharmCount()
  {
	return 0;
  }
  
  public void informTraits(Object traits)
  {
	  //do nothing
  }
}