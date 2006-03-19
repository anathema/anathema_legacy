package net.sf.anathema.character.db.template;

import net.sf.anathema.character.generic.impl.template.points.AbstractCreationPoints;
import net.sf.anathema.character.generic.impl.template.points.AttributeCreationPoints;
import net.sf.anathema.character.generic.template.points.IAttributeCreationPoints;

public abstract class AbstractDbCreationPoints extends AbstractCreationPoints {

  public int getBonusPointCount() {
    return 15;
  }

  public IAttributeCreationPoints getAttributeCreationPoints() {
    return new AttributeCreationPoints(7, 6, 4);
  }

  public int getFavoredCreationCharmCount() {
    return 4;
  }

  public int getDefaultCreationCharmCount() {
    return 3;
  }
}