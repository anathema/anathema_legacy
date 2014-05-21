package net.sf.anathema.hero.template.creation;

import net.sf.anathema.hero.template.points.IAbilityCreationPoints;
import net.sf.anathema.hero.template.points.IAttributeCreationPoints;

public interface ICreationPoints {

  int getBonusPointCount();

  int getVirtueCreationPoints();

  IAbilityCreationPoints getAbilityCreationPoints();

  IAttributeCreationPoints getAttributeCreationPoints();

  int getFavoredCreationMagicCount();

  int getDefaultCreationMagicCount();
}