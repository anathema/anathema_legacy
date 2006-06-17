package net.sf.anathema.character.impl.model.concept;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.caste.ICasteTypeVisitor;
import net.sf.anathema.character.impl.model.TypedDescription;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.character.model.concept.ICharacterConcept;
import net.sf.anathema.character.model.concept.IWillpowerRegainingConcept;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class CharacterConcept implements ICharacterConcept {

  private final ITypedDescription<ICasteType< ? extends ICasteTypeVisitor>> caste = new TypedDescription<ICasteType< ? extends ICasteTypeVisitor>>(
      ICasteType.NULL_CASTE_TYPE);
  private final ITextualDescription concept = new SimpleTextualDescription();
  private IWillpowerRegainingConcept willpowerRegaining;

  public ITypedDescription<ICasteType< ? extends ICasteTypeVisitor>> getCaste() {
    return caste;
  }

  public ITextualDescription getConcept() {
    return concept;
  }

  public CharacterConcept(IWillpowerRegainingConcept willpowerRegaining) {
    this.willpowerRegaining = willpowerRegaining;
  }

  public IWillpowerRegainingConcept getWillpowerRegainingConcept() {
    return willpowerRegaining;
  }
}