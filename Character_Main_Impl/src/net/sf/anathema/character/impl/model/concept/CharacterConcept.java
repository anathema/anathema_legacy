package net.sf.anathema.character.impl.model.concept;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.impl.model.TypedDescription;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.character.model.concept.ICharacterConcept;
import net.sf.anathema.character.model.concept.IWillpowerRegainingConcept;
import net.sf.anathema.lib.workflow.textualdescription.ISimpleTextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class CharacterConcept implements ICharacterConcept {

  private final ITypedDescription<ICasteType> caste = new TypedDescription<ICasteType>(ICasteType.NULL_CASTE_TYPE);
  private final ISimpleTextualDescription concept = new SimpleTextualDescription();
  private IWillpowerRegainingConcept willpowerRegaining;

  public ITypedDescription<ICasteType> getCaste() {
    return caste;
  }

  public ISimpleTextualDescription getConcept() {
    return concept;
  }

  public CharacterConcept(IWillpowerRegainingConcept willpowerRegaining) {
    this.willpowerRegaining = willpowerRegaining;
  }

  public IWillpowerRegainingConcept getWillpowerRegainingConcept() {
    return willpowerRegaining;
  }
}