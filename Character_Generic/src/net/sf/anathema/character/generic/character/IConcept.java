package net.sf.anathema.character.generic.character;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.caste.ICasteTypeVisitor;

public interface IConcept {

  public String getConceptText();

  public String getWillpowerRegainingConceptName();

  public ICasteType<? extends ICasteTypeVisitor>  getCasteType();
}