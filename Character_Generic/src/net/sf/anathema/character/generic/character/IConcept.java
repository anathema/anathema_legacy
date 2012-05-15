package net.sf.anathema.character.generic.character;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.lib.resources.IResources;

public interface IConcept {

  String getWillpowerRegainingConceptName();

  String getWillpowerRegainingComment(IResources resources);

  ICasteType getCasteType();
  
  int getAge();
}