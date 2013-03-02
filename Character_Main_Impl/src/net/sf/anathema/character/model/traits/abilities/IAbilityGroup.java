package net.sf.anathema.character.model.traits.abilities;

import net.sf.anathema.character.library.trait.IFavorableDefaultTrait;
import net.sf.anathema.lib.util.Identified;

public interface IAbilityGroup extends Identified {

  IFavorableDefaultTrait[] getAbilities();
}