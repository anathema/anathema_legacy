package net.sf.anathema.character.generic.impl.additional;

import net.sf.anathema.character.generic.additionalrules.IAdditionalEssencePool;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;

public class MultiLearnablePool implements IAdditionalEssencePool {

  private final IMultiLearnableCharm charm;
  private final int peripheralMultiplier;
  private final int personalMultiplier;

  public MultiLearnablePool(IMultiLearnableCharm charm, int personalMultiplier, int peripheralMultiplier) {
    this.charm = charm;
    this.personalMultiplier = personalMultiplier;
    this.peripheralMultiplier = peripheralMultiplier;
  }

  public int getAdditionalPeripheralPool(IGenericTraitCollection traitCollection, IMagicCollection magicCollection) {
    return magicCollection.getLearnCount(charm) * peripheralMultiplier;
  }

  public int getAdditionalPersonalPool(IGenericTraitCollection traitCollection, IMagicCollection magicCollection) {
    return magicCollection.getLearnCount(charm) * personalMultiplier;
  }
}