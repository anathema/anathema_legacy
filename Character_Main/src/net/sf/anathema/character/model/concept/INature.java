package net.sf.anathema.character.model.concept;

import net.sf.anathema.character.model.ITypedDescription;

public interface INature extends IWillpowerRegainingConcept {

  public ITypedDescription<INatureType> getDescription();
}