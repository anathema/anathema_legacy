package net.sf.anathema.character.db.template.lookshy;

import net.sf.anathema.character.db.template.AbstractDbCreationPoints;

public abstract class AbstractLookshyDbCreationPoints extends AbstractDbCreationPoints {

  public int getBackgroundPointCount() {
    return 13;
  }

  @Override
  public int getDefaultCreationCharmCount() {
    return 4;
  }
}