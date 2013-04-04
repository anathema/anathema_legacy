package net.sf.anathema.character.generic.character;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.lib.resources.Resources;

public interface IConcept {

  String getWillpowerRegainingConceptName();

  String getWillpowerRegainingComment(Resources resources);

  ICasteType getCasteType();
  
  int getAge();
}