package net.sf.anathema.character.generic.impl.additional;

import net.sf.anathema.character.generic.additionalrules.IAdditionalEssencePool;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;

public class MultiLearnableCharmPool implements IAdditionalEssencePool {

  private final IMultiLearnableCharm charm;
  private final AdditionalEssencePool personalPool;
  private final AdditionalEssencePool peripheralPool;

  public MultiLearnableCharmPool(
      IMultiLearnableCharm charm,
      AdditionalEssencePool personalPool,
      AdditionalEssencePool peripheralPool) {
    this.charm = charm;
    this.personalPool = personalPool;
    this.peripheralPool = peripheralPool;
  }

  public int getAdditionalPeripheralPool(IGenericTraitCollection traitCollection, IMagicCollection magicCollection) {
    return peripheralPool.getPool(magicCollection.getLearnCount(charm));
  }

  public int getAdditionalPersonalPool(IGenericTraitCollection traitCollection, IMagicCollection magicCollection) {
    return personalPool.getPool(magicCollection.getLearnCount(charm));
  }
}