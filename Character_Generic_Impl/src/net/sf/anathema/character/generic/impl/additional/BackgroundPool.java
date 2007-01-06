package net.sf.anathema.character.generic.impl.additional;

import net.sf.anathema.character.generic.additionalrules.IAdditionalEssencePool;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.traits.IGenericTrait;

public class BackgroundPool implements IAdditionalEssencePool {

  private final IBackgroundTemplate template;
  private final AdditionalEssencePool personalPool;
  private final AdditionalEssencePool peripheralPool;

  public BackgroundPool(
      IBackgroundTemplate template,
      AdditionalEssencePool personalPool,
      AdditionalEssencePool peripheralPool) {
    this.template = template;
    this.personalPool = personalPool;
    this.peripheralPool = peripheralPool;

  }

  public int getAdditionalPeripheralPool(IGenericTraitCollection traitCollection, IMagicCollection magicCollection) {
    return peripheralPool.getPool(getCurrentValue(traitCollection));
  }

  public int getAdditionalPersonalPool(IGenericTraitCollection traitCollection, IMagicCollection magicCollection) {
    return personalPool.getPool(getCurrentValue(traitCollection));
  }

  private int getCurrentValue(IGenericTraitCollection traitCollection) {
    IGenericTrait background = traitCollection.getTrait(template);
    if (background == null) {
      return 0;
    }
    return background.getCurrentValue();
  }
}