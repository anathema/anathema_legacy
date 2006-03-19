package net.sf.anathema.character.impl.model;

import net.sf.anathema.character.generic.caste.ICasteType;

public class Caste extends TypedDescription<ICasteType> {

  public Caste() {
    super(ICasteType.NULL_CASTE_TYPE);
  }
}