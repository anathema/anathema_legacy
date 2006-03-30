package net.sf.anathema.character.impl.model.concept;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.model.ICharacterConcept;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.lib.workflow.textualdescription.ISimpleTextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public abstract class AbstractCharacterConcept implements ICharacterConcept {

  private final ITypedDescription<ICasteType> caste = new Caste();
  private final ISimpleTextualDescription concept = new SimpleTextualDescription();

  public ITypedDescription<ICasteType> getCaste() {
    return caste;
  }

  public ISimpleTextualDescription getConcept() {
    return concept;
  }
}