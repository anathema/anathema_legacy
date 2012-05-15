package net.sf.anathema.character.generic.additionalrules;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.lib.util.IdentifiedInteger;

public interface IAdditionalEssencePool
{
  int getAdditionalPeripheralPool(IGenericTraitCollection traitCollection, IMagicCollection magicCollection);

  int getAdditionalPersonalPool(IGenericTraitCollection traitCollection, IMagicCollection magicCollection);
  
  IdentifiedInteger[] getAdditionalComplexPools(IGenericTraitCollection traitCollection, IMagicCollection magicCollection);
  
  boolean modifiesBasePool();
}