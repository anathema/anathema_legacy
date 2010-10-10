package net.sf.anathema.character.generic.template.creation;

import net.sf.anathema.character.generic.template.points.IFavorableTraitCreationPoints;
import net.sf.anathema.character.generic.template.points.IAttributeCreationPoints;

public interface ICreationPoints {

  public int getBackgroundPointCount();

  public int getBonusPointCount();

  public int getVirtueCreationPoints();
  
  public int getSpecialityCreationPoints();

  public IFavorableTraitCreationPoints getAbilityCreationPoints();

  public IAttributeCreationPoints getAttributeCreationPoints();

  public int getFavoredCreationCharmCount();

  public int getDefaultCreationCharmCount();
}