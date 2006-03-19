package net.sf.anathema.character.db.template.lookshy;

import net.sf.anathema.character.generic.impl.template.points.FavorableTraitCreationPoints;
import net.sf.anathema.character.generic.template.points.IFavorableTraitCreationPoints;

public class LookshyOutcasteDbCreationPoints extends AbstractLookshyDbCreationPoints {

  public IFavorableTraitCreationPoints getAbilityCreationPoints() {
    return new FavorableTraitCreationPoints(3, 10, 15);
  }
}