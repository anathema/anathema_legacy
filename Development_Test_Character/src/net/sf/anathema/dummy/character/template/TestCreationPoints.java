package net.sf.anathema.dummy.character.template;

import net.sf.anathema.character.generic.impl.template.points.AbstractCreationPoints;
import net.sf.anathema.character.generic.impl.template.points.AttributeCreationPoints;
import net.sf.anathema.character.generic.impl.template.points.AbilityCreationPoints;
import net.sf.anathema.character.generic.template.points.IAbilityCreationPoints;
import net.sf.anathema.character.generic.template.points.IAttributeCreationPoints;

public class TestCreationPoints extends AbstractCreationPoints {

  public IAttributeCreationPoints getAttributeCreationPoints() {
    return new AttributeCreationPoints(8, 6, 4);
  }

  public int getBonusPointCount() {
    return 15;
  }

  public int getBackgroundPointCount() {
    return 7;
  }
  
  public int getSpecialtyCreationPoints() {
    return 0;
  }

  public IAbilityCreationPoints getAbilityCreationPoints() {
    return new AbilityCreationPoints(5, 10, 15);
  }

  public int getFavoredCreationCharmCount() {
    return 5;
  }

  public int getDefaultCreationCharmCount() {
    return 5;
  }
  
  public int getUniqueRequiredCreationCharmCount() {
	    return 0;
  }
  
  public void informTraits(Object traits)
  {
	  //do nothing
  }
  
}