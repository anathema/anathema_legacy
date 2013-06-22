package net.sf.anathema.character.generic.impl.additional;

import net.sf.anathema.character.generic.additionalrules.IAdditionalEssencePool;
import net.sf.anathema.hero.magic.MagicCollection;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.lib.util.IdentifiedInteger;

public class LearnableCharmPool implements IAdditionalEssencePool {

  private final ICharm charm;
  private final AdditionalEssencePool personalPool;
  private final AdditionalEssencePool peripheralPool;
  private final ComplexAdditionalEssencePool[] complexPools;

  public LearnableCharmPool(ICharm charm, AdditionalEssencePool personalPool, AdditionalEssencePool peripheralPool,
                            ComplexAdditionalEssencePool... complexPools) {
    this.charm = charm;
    this.personalPool = personalPool;
    this.peripheralPool = peripheralPool;
    this.complexPools = complexPools;
  }

  @Override
  public int getAdditionalPeripheralPool(TraitMap traitMap, MagicCollection magicCollection) {
    return peripheralPool.getPool(magicCollection.isLearned(charm) ? 1 : 0);
  }

  @Override
  public int getAdditionalPersonalPool(TraitMap traitMap, MagicCollection magicCollection) {
    return personalPool.getPool(magicCollection.isLearned(charm) ? 1 : 0);
  }

  @Override
  public IdentifiedInteger[] getAdditionalComplexPools(TraitMap traitMap, MagicCollection magicCollection) {
    IdentifiedInteger[] poolValues = new IdentifiedInteger[complexPools.length];
    for (int i = 0; i < complexPools.length; i++) {
      poolValues[i] =
              new IdentifiedInteger(complexPools[i].getId(), complexPools[i].getPool(traitMap, magicCollection.isLearned(charm) ? 1 : 0));
    }
    return poolValues;
  }

  @Override
  public boolean modifiesBasePool() {
    return false;
  }
}