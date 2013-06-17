package net.sf.anathema.character.generic.additionalrules;

import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.character.main.model.traits.TraitMap;

public interface IAdditionalBonusPointPool {

  int getAmount(TraitMap traitCollection);

  boolean isAllowedForTrait(TraitMap traitCollection, GenericTrait trait);

  boolean isAllowedForMagic(TraitMap traitCollection, IMagic magic);
}