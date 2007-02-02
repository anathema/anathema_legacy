package net.sf.anathema.character.db.template.dynastic;

import net.sf.anathema.character.db.template.AbstractDbCreationPoints;
import net.sf.anathema.character.generic.impl.template.points.FavorableTraitCreationPoints;
import net.sf.anathema.character.generic.template.points.IFavorableTraitCreationPoints;

public class DynasticDbCreationPoints extends AbstractDbCreationPoints {
  public int getBackgroundPointCount() {
    return 13;
  }

  public IFavorableTraitCreationPoints getAbilityCreationPoints() {
    return new FavorableTraitCreationPoints(3, 13, 22);
  }
}