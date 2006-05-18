package net.sf.anathema.character.generic.character;

import net.sf.anathema.character.generic.caste.ICasteType;

public interface IConcept {

  public String getConceptText();

  public String getWillpowerRegainingConceptName();

  public ICasteType getCasteType();
}