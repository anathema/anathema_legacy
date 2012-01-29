package net.sf.anathema.character.generic.impl.additional;

import net.sf.anathema.character.generic.additionalrules.IAdditionalEssencePool;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.util.IdentifiedInteger;

public class LearnableCharmPool implements IAdditionalEssencePool {

  private final ICharm charm;
  private final AdditionalEssencePool personalPool;
  private final AdditionalEssencePool peripheralPool;
  private final ComplexAdditionalEssencePool[] complexPools;

  public LearnableCharmPool(
      ICharm charm,
      AdditionalEssencePool personalPool,
      AdditionalEssencePool peripheralPool,
      ComplexAdditionalEssencePool... complexPools) {
    this.charm = charm;
    this.personalPool = personalPool;
    this.peripheralPool = peripheralPool;
    this.complexPools = complexPools;
  }

  public int getAdditionalPeripheralPool(IGenericTraitCollection traitCollection, IMagicCollection magicCollection) {
    return peripheralPool.getPool(magicCollection.isLearned(charm) ? 1 : 0);
  }

  public int getAdditionalPersonalPool(IGenericTraitCollection traitCollection, IMagicCollection magicCollection) {
    return personalPool.getPool(magicCollection.isLearned(charm) ? 1 : 0);
  }

  public IdentifiedInteger[] getAdditionalComplexPools(IGenericTraitCollection traitCollection,
                                                       IMagicCollection magicCollection) {
    IdentifiedInteger[] poolValues = new IdentifiedInteger[complexPools.length];
    for (int i = 0; i < complexPools.length; i++) {
      poolValues[i] = new IdentifiedInteger(complexPools[i].getId(),
                                            complexPools[i].getPool(traitCollection,
                                                    magicCollection.isLearned(charm) ? 1 : 0));
    }
    return poolValues;
  }
}