package net.sf.anathema.character.generic.additionalrules;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.traits.IGenericTrait;

public interface IAdditionalBonusPointPool {

  int getAmount(IGenericTraitCollection traitCollection);

  boolean isAllowedForTrait(IGenericTraitCollection traitCollection, IGenericTrait trait);

  boolean isAllowedForMagic(IGenericTraitCollection traitCollection, IMagic magic);
}