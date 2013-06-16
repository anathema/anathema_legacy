package net.sf.anathema.character.library.trait.subtrait;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.IDefaultTrait;

public interface ISubTrait extends IDefaultTrait {

  String getName();

  ITraitType getBasicTraitType();
}