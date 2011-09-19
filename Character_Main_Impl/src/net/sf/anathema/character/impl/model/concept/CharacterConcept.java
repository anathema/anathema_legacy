package net.sf.anathema.character.impl.model.concept;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.impl.model.TypedDescription;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.character.model.concept.ICharacterConcept;
import net.sf.anathema.character.model.concept.IWillpowerRegainingConcept;

public class CharacterConcept implements ICharacterConcept {

  private final ITypedDescription<ICasteType> caste = new TypedDescription<ICasteType>(ICasteType.NULL_CASTE_TYPE);
  private IWillpowerRegainingConcept willpowerRegaining;
  private int age = 0;

  public CharacterConcept(IWillpowerRegainingConcept willpowerRegaining) {
    this.willpowerRegaining = willpowerRegaining;
  }

  public ITypedDescription<ICasteType> getCaste() {
    return caste;
  }
  
  public int getAge() {
	  return age;
  }

  public IWillpowerRegainingConcept getWillpowerRegainingConcept() {
    return willpowerRegaining;
  }
  
  public void setAge(int age) {
    this.age = age;
  }
}