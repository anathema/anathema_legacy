package net.sf.anathema.character.db.template.cult;

import net.sf.anathema.character.db.template.outcaste.AbstractOutcasteDbCreationPoints;
import net.sf.anathema.character.generic.impl.template.points.FavorableTraitCreationPoints;
import net.sf.anathema.character.generic.template.points.IFavorableTraitCreationPoints;

public class CultDbCreationPoints extends AbstractOutcasteDbCreationPoints {

  public IFavorableTraitCreationPoints getAbilityCreationPoints() {
    return new FavorableTraitCreationPoints(3, 10, 20);
  }
}
