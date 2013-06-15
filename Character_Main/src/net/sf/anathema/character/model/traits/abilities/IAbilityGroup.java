package net.sf.anathema.character.model.traits.abilities;

import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.lib.util.Identifier;

public interface IAbilityGroup extends Identifier {

  IDefaultTrait[] getAbilities();
}