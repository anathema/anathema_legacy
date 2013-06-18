package net.sf.anathema.character.main.model.traits;

import net.sf.anathema.character.change.ChangeFlavor;
import net.sf.anathema.character.generic.traits.TraitType;

public class TraitChangeFlavor extends ChangeFlavor {

  private TraitType traitType;

  public TraitChangeFlavor(TraitType traitType) {
    super(traitType.getId());
    this.traitType = traitType;
  }

  public TraitType getTraitType() {
    return traitType;
  }
}
