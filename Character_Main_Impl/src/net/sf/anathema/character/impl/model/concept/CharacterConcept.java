package net.sf.anathema.character.impl.model.concept;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.impl.model.TypedDescription;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.character.model.concept.ICharacterConcept;
import net.sf.anathema.character.model.concept.IWillpowerRegainingConcept;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;

public class CharacterConcept implements ICharacterConcept {

  private final ITypedDescription<ICasteType> caste = new TypedDescription<ICasteType>(ICasteType.NULL_CASTE_TYPE);
  private IWillpowerRegainingConcept willpowerRegaining;
  private IntegerSpinner ageSpinner;

  public ITypedDescription<ICasteType> getCaste() {
    return caste;
  }
  
  public IntegerSpinner getAgeSpinner()
  {
	  return ageSpinner;
  }

  public CharacterConcept(IWillpowerRegainingConcept willpowerRegaining) {
    this.willpowerRegaining = willpowerRegaining;
    this.ageSpinner = new IntegerSpinner(0);
    ageSpinner.setPreferredWidth(48);
    ageSpinner.setStepSize(5);
  }

  public IWillpowerRegainingConcept getWillpowerRegainingConcept() {
    return willpowerRegaining;
  }
}