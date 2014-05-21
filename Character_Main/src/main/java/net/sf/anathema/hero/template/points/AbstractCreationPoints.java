package net.sf.anathema.hero.template.points;

import net.sf.anathema.hero.template.creation.ICreationPoints;

public abstract class AbstractCreationPoints implements ICreationPoints {

  @Override
  public final int getVirtueCreationPoints() {
    return 5;
  }
}