package net.sf.anathema.character.impl.model.concept;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.impl.model.TypedDescription;
import net.sf.anathema.character.model.ICharacterConcept;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.character.model.nature.INatureType;
import net.sf.anathema.lib.workflow.textualdescription.ISimpleTextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class CharacterConcept implements ICharacterConcept {

  private final ITypedDescription<INatureType> nature = new TypedDescription<INatureType>();
  private final ITypedDescription<ICasteType> caste = new Caste();
  private final ISimpleTextualDescription concept = new SimpleTextualDescription();

  public ITypedDescription<INatureType> getNature() {
    return nature;
  }

  public ITypedDescription<ICasteType> getCaste() {
    return caste;
  }

  public ISimpleTextualDescription getConcept() {
    return concept;
  }
}