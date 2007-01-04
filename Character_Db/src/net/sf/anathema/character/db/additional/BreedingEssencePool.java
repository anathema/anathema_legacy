package net.sf.anathema.character.db.additional;

import net.sf.anathema.character.generic.additionalrules.IAdditionalEssencePool;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.IGenericTrait;

public class BreedingEssencePool implements IAdditionalEssencePool {

  private final IBackgroundTemplate breedingTemplate;

  public BreedingEssencePool(IBackgroundTemplate breedingTemplate) {
    this.breedingTemplate = breedingTemplate;
  }

  public int getAdditionalPeripheralPool(IGenericTraitCollection traitCollection, IMagicCollection magicCollection) {
    IGenericTrait breeding = traitCollection.getTrait(breedingTemplate);
    if (breeding == null) {
      return 0;
    }
    int currentValue = breeding.getCurrentValue();
    if (currentValue == 1) {
      return 2;
    }
    return Math.max(0, currentValue * 2 - 1);
  }

  public int getAdditionalPersonalPool(IGenericTraitCollection traitCollection, IMagicCollection magicCollection) {
    IGenericTrait breeding = traitCollection.getTrait(breedingTemplate);
    return breeding == null ? 0 : breeding.getCurrentValue();
  }
}