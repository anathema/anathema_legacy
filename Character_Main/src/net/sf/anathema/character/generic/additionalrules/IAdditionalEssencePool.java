package net.sf.anathema.character.generic.additionalrules;

import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.main.traits.model.TraitMap;
import net.sf.anathema.lib.util.IdentifiedInteger;

public interface IAdditionalEssencePool
{
  int getAdditionalPeripheralPool(TraitMap traitMap, IMagicCollection magicCollection);

  int getAdditionalPersonalPool(TraitMap traitMap, IMagicCollection magicCollection);
  
  IdentifiedInteger[] getAdditionalComplexPools(TraitMap traitMap, IMagicCollection magicCollection);
  
  boolean modifiesBasePool();
}