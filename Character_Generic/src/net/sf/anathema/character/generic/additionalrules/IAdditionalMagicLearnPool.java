package net.sf.anathema.character.generic.additionalrules;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.IMagic;

public interface IAdditionalMagicLearnPool {

  public boolean isAllowedFor(IGenericCharacter characterAbstraction, IMagic magic);
  
  public int getAdditionalMagicCount(IGenericCharacter characterAbstraction);
}