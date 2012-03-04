package net.sf.anathema.character.generic.dummy.template;

import net.sf.anathema.character.generic.impl.template.points.AbilityCreationPoints;
import net.sf.anathema.character.generic.impl.template.points.AbstractCreationPoints;
import net.sf.anathema.character.generic.impl.template.points.AttributeCreationPoints;
import net.sf.anathema.character.generic.template.points.IAbilityCreationPoints;
import net.sf.anathema.character.generic.template.points.IAttributeCreationPoints;

public class TestCreationPoints extends AbstractCreationPoints {

  @Override
  public IAttributeCreationPoints getAttributeCreationPoints() {
    return new AttributeCreationPoints(8, 6, 4);
  }

  @Override
  public int getBonusPointCount() {
    return 15;
  }

  @Override
  public int getBackgroundPointCount() {
    return 7;
  }
  
  @Override
  public int getSpecialtyCreationPoints() {
    return 0;
  }

  @Override
  public IAbilityCreationPoints getAbilityCreationPoints() {
    return new AbilityCreationPoints(5, 10, 15);
  }

  @Override
  public int getFavoredCreationCharmCount() {
    return 5;
  }

  @Override
  public int getDefaultCreationCharmCount() {
    return 5;
  }
  
  @Override
  public int getUniqueRequiredCreationCharmCount() {
	    return 0;
  }
  
  @Override
  public void informTraits(Object traits)
  {
	  //do nothing
  }
  
}