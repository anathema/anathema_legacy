package net.sf.anathema.character.impl.model.concept;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.impl.model.TypedDescription;

public class Caste extends TypedDescription<ICasteType> {

  public Caste() {
    super(ICasteType.NULL_CASTE_TYPE);
  }
}