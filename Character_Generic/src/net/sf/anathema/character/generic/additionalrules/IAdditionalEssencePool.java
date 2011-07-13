package net.sf.anathema.character.generic.additionalrules;

import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.lib.util.IdentifiedInteger;

public interface IAdditionalEssencePool {

  public int getAdditionalPeripheralPool(IGenericTraitCollection traitCollection, IMagicCollection magicCollection);

  public int getAdditionalPersonalPool(IGenericTraitCollection traitCollection, IMagicCollection magicCollection);
  
  public IdentifiedInteger[] getAdditionalComplexPools(IGenericTraitCollection traitCollection, IMagicCollection magicCollection);
}