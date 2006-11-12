package net.sf.anathema.character.generic.impl.traits.caste;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.ILimitationContext;

public class StaticCasteMinimum implements ICasteTraitMinimum {

  private final ICasteType caste;
  private int minimum;

  public StaticCasteMinimum(ICasteType casteType, int minimum) {
    this.caste = casteType;
    this.minimum = minimum;
  }

  public ICasteType getCaste() {
    return caste;
  }

  public int getMinimumValue(ILimitationContext limitationContext) {
    return minimum;
  }
}