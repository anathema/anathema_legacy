package net.sf.anathema.character.generic.impl.traits.caste;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.caste.ICasteTypeVisitor;
import net.sf.anathema.character.generic.character.ILimitationContext;

public class StaticCasteMinimum implements ICasteTraitMinimum {

  private final ICasteType< ? extends ICasteTypeVisitor> caste;
  private int minimum;

  public StaticCasteMinimum(ICasteType< ? extends ICasteTypeVisitor> casteType, int minimum) {
    this.caste = casteType;
    this.minimum = minimum;
  }

  public ICasteType< ? extends ICasteTypeVisitor> getCaste() {
    return caste;
  }

  public int getMinimumValue(ILimitationContext limitationContext) {
    return minimum;
  }
}