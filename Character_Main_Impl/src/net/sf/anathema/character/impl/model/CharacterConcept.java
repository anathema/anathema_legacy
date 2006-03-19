package net.sf.anathema.character.impl.model;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.model.ICharacterConcept;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.character.model.nature.INatureType;
import net.sf.anathema.lib.workflow.textualdescription.ISimpleTextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class CharacterConcept implements ICharacterConcept {

  private ITypedDescription<ICasteType> caste;
  private final ITypedDescription<INatureType> nature = new TypedDescription<INatureType>();
  private final ISimpleTextualDescription concept = new SimpleTextualDescription();

  public CharacterConcept() {
    caste = new Caste();
  }

  public ITypedDescription<ICasteType> getCaste() {
    return caste;
  }

  public ITypedDescription<INatureType> getNature() {
    return nature;
  }

  public ISimpleTextualDescription getConcept() {
    return concept;
  }
}