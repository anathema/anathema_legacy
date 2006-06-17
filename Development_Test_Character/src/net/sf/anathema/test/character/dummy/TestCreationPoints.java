package net.sf.anathema.test.character.dummy;

import net.sf.anathema.character.generic.impl.template.points.AbstractCreationPoints;
import net.sf.anathema.character.generic.impl.template.points.AttributeCreationPoints;
import net.sf.anathema.character.generic.impl.template.points.FavorableTraitCreationPoints;
import net.sf.anathema.character.generic.template.points.IAttributeCreationPoints;
import net.sf.anathema.character.generic.template.points.IFavorableTraitCreationPoints;

public class TestCreationPoints extends AbstractCreationPoints {

  public IAttributeCreationPoints getAttributeCreationPoints() {
    return new AttributeCreationPoints(8, 6, 4);
  }

  public int getBonusPointCount() {
    return 15;
  }

  public int getBackgroundPointCount() {
    return 7;
  }

  public IFavorableTraitCreationPoints getAbilityCreationPoints() {
    return new FavorableTraitCreationPoints(5, 10, 15);
  }

  public int getFavoredCreationCharmCount() {
    return 5;
  }

  public int getDefaultCreationCharmCount() {
    return 5;
  }
}