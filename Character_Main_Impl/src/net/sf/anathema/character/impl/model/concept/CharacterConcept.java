package net.sf.anathema.character.impl.model.concept;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.impl.model.IntegerDescription;
import net.sf.anathema.character.impl.model.TypedDescription;
import net.sf.anathema.character.model.IIntegerDescription;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.character.model.concept.ICharacterConcept;
import net.sf.anathema.character.model.concept.IMotivation;

public class CharacterConcept implements ICharacterConcept {

  private final ITypedDescription<ICasteType> caste = new TypedDescription<ICasteType>(ICasteType.NULL_CASTE_TYPE);
  private IMotivation willpowerRegaining;
  private IIntegerDescription age = new IntegerDescription(0);

  public CharacterConcept(IMotivation willpowerRegaining) {
    this.willpowerRegaining = willpowerRegaining;
  }

  @Override
  public ITypedDescription<ICasteType> getCaste() {
    return caste;
  }
  
  @Override
  public IIntegerDescription getAge() {
	  return age;
  }

  @Override
  public IMotivation getWillpowerRegainingConcept() {
    return willpowerRegaining;
  }
}