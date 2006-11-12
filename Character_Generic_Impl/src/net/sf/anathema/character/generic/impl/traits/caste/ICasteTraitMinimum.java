package net.sf.anathema.character.generic.impl.traits.caste;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.ILimitationContext;

public interface ICasteTraitMinimum {

  public ICasteType getCaste();

  public int getMinimumValue(ILimitationContext limitationContext);
}