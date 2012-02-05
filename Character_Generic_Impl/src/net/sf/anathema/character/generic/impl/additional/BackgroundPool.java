package net.sf.anathema.character.generic.impl.additional;

import net.sf.anathema.character.generic.additionalrules.IAdditionalEssencePool;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.lib.util.IdentifiedInteger;

public class BackgroundPool implements IAdditionalEssencePool {

  private final IBackgroundTemplate template;
  private final AdditionalEssencePool personalPool;
  private final AdditionalEssencePool peripheralPool;
  private final ComplexAdditionalEssencePool[] complexPools;

  public BackgroundPool(
      IBackgroundTemplate template,
      AdditionalEssencePool personalPool,
      AdditionalEssencePool peripheralPool,
      ComplexAdditionalEssencePool[] complexPools) {
    this.template = template;
    this.personalPool = personalPool;
    this.peripheralPool = peripheralPool;
    this.complexPools = complexPools;
  }

  public int getAdditionalPeripheralPool(IGenericTraitCollection traitCollection, IMagicCollection magicCollection) {
    return peripheralPool.getPool(getCurrentValue(traitCollection));
  }

  public int getAdditionalPersonalPool(IGenericTraitCollection traitCollection, IMagicCollection magicCollection) {
    return personalPool.getPool(getCurrentValue(traitCollection));
  }

  public IdentifiedInteger[] getAdditionalComplexPools(IGenericTraitCollection traitCollection,
                                                       IMagicCollection magicCollection) {
    IdentifiedInteger[] poolValues = new IdentifiedInteger[complexPools.length];
    for (int i = 0; i < complexPools.length; i++) {
      poolValues[i] = new IdentifiedInteger(complexPools[i].getId(),
                                            complexPools[i].getPool(traitCollection,
                                                    getCurrentValue(traitCollection)));
    }
    return poolValues;
  }

  private int getCurrentValue(IGenericTraitCollection traitCollection) {
    if(template == null){
      return 0;
    }
    IGenericTrait background = traitCollection.getTrait(template);
    if (background == null) {
      return 0;
    }
    return background.getCurrentValue();
  }
}