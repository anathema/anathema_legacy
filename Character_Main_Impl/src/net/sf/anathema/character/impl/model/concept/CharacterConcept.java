package net.sf.anathema.character.impl.model.concept;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.impl.model.TypedDescription;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.character.model.concept.ICharacterConcept;
import net.sf.anathema.character.model.concept.IWillpowerRegainingConcept;

public class CharacterConcept implements ICharacterConcept {

  private final ITypedDescription<ICasteType> caste = new TypedDescription<ICasteType>(ICasteType.NULL_CASTE_TYPE);
  private IWillpowerRegainingConcept willpowerRegaining;

  public ITypedDescription<ICasteType> getCaste() {
    return caste;
  }

  public CharacterConcept(IWillpowerRegainingConcept willpowerRegaining) {
    this.willpowerRegaining = willpowerRegaining;
  }

  public IWillpowerRegainingConcept getWillpowerRegainingConcept() {
    return willpowerRegaining;
  }
}