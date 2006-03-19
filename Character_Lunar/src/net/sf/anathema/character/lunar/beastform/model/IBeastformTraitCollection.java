package net.sf.anathema.character.lunar.beastform.model;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.ITraitCollection;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformAttribute;

public interface IBeastformTraitCollection extends ITraitCollection {

  public IBeastformAttribute getDeadlyBeastmanAttribute(ITraitType traitType);
}