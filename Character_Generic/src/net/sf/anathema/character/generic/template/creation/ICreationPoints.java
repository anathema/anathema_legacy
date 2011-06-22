package net.sf.anathema.character.generic.template.creation;

import net.sf.anathema.character.generic.template.points.IAbilityCreationPoints;
import net.sf.anathema.character.generic.template.points.IAttributeCreationPoints;

public interface ICreationPoints {

  public int getBackgroundPointCount();

  public int getBonusPointCount();

  public int getVirtueCreationPoints();
  
  public int getSpecialtyCreationPoints();

  public IAbilityCreationPoints getAbilityCreationPoints();

  public IAttributeCreationPoints getAttributeCreationPoints();

  public int getFavoredCreationCharmCount();

  public int getDefaultCreationCharmCount();
  
  public int getUniqueRequiredCreationCharmCount();
  
  public void informTraits(Object traits);
}