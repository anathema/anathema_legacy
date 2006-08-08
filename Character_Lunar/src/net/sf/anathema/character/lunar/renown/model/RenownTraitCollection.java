package net.sf.anathema.character.lunar.renown.model;

import net.sf.anathema.character.library.trait.AbstractTraitCollection;
import net.sf.anathema.character.library.trait.IModifiableTrait;

public class RenownTraitCollection extends AbstractTraitCollection {

  public void addRenownTrait(IModifiableTrait trait) {
    super.addTrait(trait);
  }
}