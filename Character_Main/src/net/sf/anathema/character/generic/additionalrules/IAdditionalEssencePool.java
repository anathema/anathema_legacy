package net.sf.anathema.character.generic.additionalrules;

import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.hero.magic.MagicCollection;
import net.sf.anathema.lib.util.IdentifiedInteger;

public interface IAdditionalEssencePool
{
  int getAdditionalPeripheralPool(TraitMap traitMap, MagicCollection magicCollection);

  int getAdditionalPersonalPool(TraitMap traitMap, MagicCollection magicCollection);
  
  IdentifiedInteger[] getAdditionalComplexPools(TraitMap traitMap, MagicCollection magicCollection);
  
  boolean modifiesBasePool();
}