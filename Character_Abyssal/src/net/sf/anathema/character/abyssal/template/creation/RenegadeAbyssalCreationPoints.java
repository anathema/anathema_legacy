package net.sf.anathema.character.abyssal.template.creation;

import net.sf.anathema.character.generic.impl.template.points.AbilityCreationPoints;
import net.sf.anathema.character.generic.impl.template.points.AbstractCreationPoints;
import net.sf.anathema.character.generic.impl.template.points.AttributeCreationPoints;
import net.sf.anathema.character.generic.template.points.IAbilityCreationPoints;
import net.sf.anathema.character.generic.template.points.IAttributeCreationPoints;

public class RenegadeAbyssalCreationPoints extends AbstractCreationPoints {

  @Override
  public int getBackgroundPointCount() {
    return 5;
  }

  @Override
  public final IAttributeCreationPoints getAttributeCreationPoints() {
    return new AttributeCreationPoints(8, 6, 4);
  }

  @Override
  public final int getBonusPointCount() {
    return 15;
  }
  
  @Override
  public int getSpecialtyCreationPoints() {
    return 0;
  }

  @Override
  public final IAbilityCreationPoints getAbilityCreationPoints() {
    return new AbilityCreationPoints(5, 10, 15);
  }

  @Override
  public final int getDefaultCreationCharmCount() {
    return 5;
  }

  @Override
  public final int getFavoredCreationCharmCount() {
    return 5;
  }
  
  @Override
  public int getUniqueRequiredCreationCharmCount()
  {
	return 0;
  }
}