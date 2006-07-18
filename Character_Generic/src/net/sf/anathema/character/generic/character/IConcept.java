package net.sf.anathema.character.generic.character;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.caste.ICasteTypeVisitor;
import net.sf.anathema.lib.resources.IResources;

public interface IConcept {

  public String getConceptText();

  public String getWillpowerRegainingConceptName();
  
  public String getWillpowerRegainingComment(IResources resources);
  
  public ICasteType<? extends ICasteTypeVisitor>  getCasteType();
}