package net.sf.anathema.character.db.template.outcaste;

import net.sf.anathema.character.generic.impl.template.points.FavorableTraitCreationPoints;
import net.sf.anathema.character.generic.template.points.IFavorableTraitCreationPoints;

public class PatricianOutcasteDbCreationPoints extends AbstractOutcasteDbCreationPoints {

  public IFavorableTraitCreationPoints getAbilityCreationPoints() {
    return new FavorableTraitCreationPoints(3, 13, 17);
  }
}