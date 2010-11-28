package net.sf.anathema.character.generic.additionalrules;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.IMagic;

public interface IAdditionalMagicLearnPool {

  public boolean isAllowedFor(IGenericTraitCollection characterAbstraction, IMagic magic);

  public int getAdditionalMagicCount(IGenericTraitCollection traitCollection);
}