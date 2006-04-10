package net.sf.anathema.character.model.concept;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.lib.workflow.textualdescription.ISimpleTextualDescription;

public interface ICharacterConcept {

  public ITypedDescription<ICasteType> getCaste();

  public ITypedDescription<INatureType> getNature();

  public ISimpleTextualDescription getConcept();
}