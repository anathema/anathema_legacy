package net.sf.anathema.character.sidereal.template;

import net.sf.anathema.character.generic.impl.template.points.AbilityCreationPoints;
import net.sf.anathema.character.generic.impl.template.points.AbstractCreationPoints;
import net.sf.anathema.character.generic.impl.template.points.AttributeCreationPoints;
import net.sf.anathema.character.generic.template.points.IAbilityCreationPoints;
import net.sf.anathema.character.generic.template.points.IAttributeCreationPoints;
import net.sf.anathema.character.generic.template.points.IFavorableTraitCreationPoints;

public class SiderealCreationPoints extends AbstractCreationPoints {

  @Override
  public int getBackgroundPointCount() {
    return 15;
  }

  @Override
  public int getBonusPointCount() {
    return 18;
  }
  
  @Override
  public int getSpecialtyCreationPoints() {
    return 0;
  }

  @Override
  public IAbilityCreationPoints getAbilityCreationPoints() {
    return new AbilityCreationPoints(4, 10, 25);
  }

  @Override
  public IAttributeCreationPoints getAttributeCreationPoints() {
    return new AttributeCreationPoints(8, 6, 4);
  }

  @Override
  public int getFavoredCreationCharmCount() {
    return 5;
  }

  @Override
  public int getDefaultCreationCharmCount() {
    return 7;
  }
  
  @Override
  public int getUniqueRequiredCreationCharmCount() {
	    return 0;
	  }

  public IFavorableTraitCreationPoints getCollegeCreationPoints() {
    return new AbilityCreationPoints(0, 4, 3);
  }
  
  @Override
  public void informTraits(Object traits)
  {
	  //do nothing
  }
}