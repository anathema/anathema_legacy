package net.sf.anathema.character.generic.template.creation;

import net.sf.anathema.character.generic.template.points.IAbilityCreationPoints;
import net.sf.anathema.character.generic.template.points.IAttributeCreationPoints;

public interface ICreationPoints {

  int getBackgroundPointCount();

  int getBonusPointCount();

  int getVirtueCreationPoints();

  int getSpecialtyCreationPoints();

  IAbilityCreationPoints getAbilityCreationPoints();

  IAttributeCreationPoints getAttributeCreationPoints();

  int getFavoredCreationCharmCount();

  int getDefaultCreationCharmCount();

  int getUniqueRequiredCreationCharmCount();
}