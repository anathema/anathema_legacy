package net.sf.anathema.character.db.template.pirates;

import net.sf.anathema.character.db.template.AbstractDbCreationPoints;
import net.sf.anathema.character.generic.impl.template.points.FavorableTraitCreationPoints;
import net.sf.anathema.character.generic.template.points.IFavorableTraitCreationPoints;

public class PirateOutcasteDbCreationPoints extends AbstractDbCreationPoints {
  public int getBackgroundPointCount() {
    return 12;
  }

  public IFavorableTraitCreationPoints getAbilityCreationPoints() {
    return new FavorableTraitCreationPoints(3, 10, 15);
  }
}