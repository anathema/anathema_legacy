package net.sf.anathema.character.abyssal.template.creation;

import net.sf.anathema.character.generic.impl.template.points.FavorableTraitCreationPoints;
import net.sf.anathema.character.generic.impl.template.points.AbstractCreationPoints;
import net.sf.anathema.character.generic.impl.template.points.AttributeCreationPoints;
import net.sf.anathema.character.generic.template.points.IFavorableTraitCreationPoints;
import net.sf.anathema.character.generic.template.points.IAttributeCreationPoints;

public class RenegadeAbyssalCreationPoints extends AbstractCreationPoints {

  public int getBackgroundPointCount() {
    return 5;
  }

  public final IAttributeCreationPoints getAttributeCreationPoints() {
    return new AttributeCreationPoints(8, 6, 4);
  }

  public final int getBonusPointCount() {
    return 15;
  }
  
  public int getSpecialtyCreationPoints() {
    return 0;
  }

  public final IFavorableTraitCreationPoints getAbilityCreationPoints() {
    return new FavorableTraitCreationPoints(5, 10, 15);
  }

  public final int getDefaultCreationCharmCount() {
    return 5;
  }

  public final int getFavoredCreationCharmCount() {
    return 5;
  }
}