package net.sf.anathema.character.main.template.creation;

import net.sf.anathema.character.main.template.points.IAbilityCreationPoints;
import net.sf.anathema.character.main.template.points.IAttributeCreationPoints;

public interface ICreationPoints {

  int getBonusPointCount();

  int getVirtueCreationPoints();

  int getSpecialtyCreationPoints();

  IAbilityCreationPoints getAbilityCreationPoints();

  IAttributeCreationPoints getAttributeCreationPoints();

  int getFavoredCreationCharmCount();

  int getDefaultCreationCharmCount();
}