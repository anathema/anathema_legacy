package net.sf.anathema.character.impl.model.concept;

import net.sf.anathema.character.impl.model.TypedDescription;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.character.model.nature.INatureType;

public class CharacterConcept extends AbstractCharacterConcept {

  private final ITypedDescription<INatureType> nature = new TypedDescription<INatureType>();

  public ITypedDescription<INatureType> getNature() {
    return nature;
  }
}