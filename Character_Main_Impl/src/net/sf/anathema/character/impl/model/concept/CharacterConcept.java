package net.sf.anathema.character.impl.model.concept;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.impl.model.IntegerDescription;
import net.sf.anathema.character.impl.model.TypedDescription;
import net.sf.anathema.character.model.IIntegerDescription;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.character.model.concept.ICharacterConcept;
import net.sf.anathema.character.model.concept.IWillpowerRegainingConcept;

public class CharacterConcept implements ICharacterConcept {

  private final ITypedDescription<ICasteType> caste = new TypedDescription<ICasteType>(ICasteType.NULL_CASTE_TYPE);
  private IWillpowerRegainingConcept willpowerRegaining;
  private IIntegerDescription age = new IntegerDescription(0);

  public CharacterConcept(IWillpowerRegainingConcept willpowerRegaining) {
    this.willpowerRegaining = willpowerRegaining;
  }

  public ITypedDescription<ICasteType> getCaste() {
    return caste;
  }
  
  public IIntegerDescription getAge() {
	  return age;
  }

  public IWillpowerRegainingConcept getWillpowerRegainingConcept() {
    return willpowerRegaining;
  }
}