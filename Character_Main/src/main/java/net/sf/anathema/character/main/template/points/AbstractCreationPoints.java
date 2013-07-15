package net.sf.anathema.character.main.template.points;

import net.sf.anathema.character.main.template.creation.ICreationPoints;

public abstract class AbstractCreationPoints implements ICreationPoints {

  @Override
  public final int getVirtueCreationPoints() {
    return 5;
  }
}