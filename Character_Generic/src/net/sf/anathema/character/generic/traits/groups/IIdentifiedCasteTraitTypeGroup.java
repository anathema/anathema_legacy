package net.sf.anathema.character.generic.traits.groups;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface IIdentifiedCasteTraitTypeGroup extends IIdentifiedTraitTypeGroup {

  public ICasteType getGroupCasteType();
  
  public ICasteType[] getTraitCasteTypes(ITraitType type);
}