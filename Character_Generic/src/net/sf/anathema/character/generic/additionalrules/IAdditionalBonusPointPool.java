package net.sf.anathema.character.generic.additionalrules;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.traits.IGenericTrait;

public interface IAdditionalBonusPointPool {

  public int getAmount(IGenericCharacter characterAbstraction);

  public boolean isAllowedForTrait(IGenericCharacter characterAbstraction, IGenericTrait trait);

  public boolean isAllowedForMagic(IGenericCharacter characterAbstraction, IMagic magic);
}