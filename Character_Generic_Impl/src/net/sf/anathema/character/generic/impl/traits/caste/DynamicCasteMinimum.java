package net.sf.anathema.character.generic.impl.traits.caste;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.traits.ITraitMinimum;

public class DynamicCasteMinimum implements ICasteTraitMinimum {

  private final ITraitMinimum minimum;
  private final ICasteType caste;

  public DynamicCasteMinimum(ICasteType caste, ITraitMinimum range) {
    this.caste = caste;
    this.minimum = range;
  }

  public ICasteType getCaste() {
    return caste;
  }

  public int getMinimumValue(ILimitationContext limitationContext) {
    return minimum.getMinimumValue(limitationContext);
  }
}