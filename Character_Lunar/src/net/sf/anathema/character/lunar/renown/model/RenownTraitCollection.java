package net.sf.anathema.character.lunar.renown.model;

import net.sf.anathema.character.library.trait.AbstractTraitCollection;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;

public class RenownTraitCollection extends AbstractTraitCollection {

  public void addRenownTrait(IDefaultTrait trait) {
    super.addTrait(trait);
  }
}