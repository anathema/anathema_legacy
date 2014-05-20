package net.sf.anathema.character.main.template.creation;

import net.sf.anathema.character.main.template.points.IAbilityCreationPoints;
import net.sf.anathema.character.main.template.points.IAttributeCreationPoints;

public interface ICreationPoints {

  int getBonusPointCount();

  int getVirtueCreationPoints();

  IAbilityCreationPoints getAbilityCreationPoints();

  IAttributeCreationPoints getAttributeCreationPoints();

  int getFavoredCreationMagicCount();

  int getDefaultCreationMagicCount();
}