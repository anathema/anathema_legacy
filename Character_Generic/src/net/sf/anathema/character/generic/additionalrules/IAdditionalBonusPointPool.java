package net.sf.anathema.character.generic.additionalrules;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.traits.IGenericTrait;

public interface IAdditionalBonusPointPool {

  public int getAmount(IGenericTraitCollection traitCollection);

  public boolean isAllowedForTrait(IGenericTraitCollection traitCollection, IGenericTrait trait);

  public boolean isAllowedForMagic(IGenericTraitCollection traitCollection, IMagic magic);
}