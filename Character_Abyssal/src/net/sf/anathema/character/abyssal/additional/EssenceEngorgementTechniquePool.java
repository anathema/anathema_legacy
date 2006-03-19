package net.sf.anathema.character.abyssal.additional;

import net.sf.anathema.character.generic.additionalrules.IAdditionalEssencePool;
import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;

public class EssenceEngorgementTechniquePool implements IAdditionalEssencePool {

  private final IMultiLearnableCharm technique;

  public EssenceEngorgementTechniquePool(IMultiLearnableCharm technique) {
    this.technique = technique;
  }

  public int getAdditionaPeripheralPool(IGenericTraitCollection traitCollection, IMagicCollection magicCollection) {
    return magicCollection.getLearnCount(technique) * 10;
  }
  
  public int getAdditionaPersonalPool(IGenericTraitCollection traitCollection, IMagicCollection magicCollection) {
    return 0;
  }
}