package net.sf.anathema.character.sidereal.template;

import net.sf.anathema.character.generic.impl.template.points.AbstractCreationPoints;
import net.sf.anathema.character.generic.impl.template.points.AttributeCreationPoints;
import net.sf.anathema.character.generic.impl.template.points.AbilityCreationPoints;
import net.sf.anathema.character.generic.template.points.IAbilityCreationPoints;
import net.sf.anathema.character.generic.template.points.IAttributeCreationPoints;
import net.sf.anathema.character.generic.template.points.IFavorableTraitCreationPoints;

public class SiderealCreationPoints extends AbstractCreationPoints {

  public int getBackgroundPointCount() {
    return 15;
  }

  public int getBonusPointCount() {
    return 18;
  }
  
  public int getSpecialtyCreationPoints() {
    return 0;
  }

  public IAbilityCreationPoints getAbilityCreationPoints() {
    return new AbilityCreationPoints(4, 10, 25);
  }

  public IAttributeCreationPoints getAttributeCreationPoints() {
    return new AttributeCreationPoints(8, 6, 4);
  }

  public int getFavoredCreationCharmCount() {
    return 5;
  }

  public int getDefaultCreationCharmCount() {
    return 7;
  }
  
  public int getUniqueRequiredCreationCharmCount() {
	    return 0;
	  }

  public IFavorableTraitCreationPoints getCollegeCreationPoints() {
    return new AbilityCreationPoints(0, 4, 3);
  }
}