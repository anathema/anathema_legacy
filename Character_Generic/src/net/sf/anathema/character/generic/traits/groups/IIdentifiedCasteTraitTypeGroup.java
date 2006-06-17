package net.sf.anathema.character.generic.traits.groups;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.caste.ICasteTypeVisitor;

public interface IIdentifiedCasteTraitTypeGroup extends IIdentifiedTraitTypeGroup {

  public ICasteType<? extends ICasteTypeVisitor>  getCasteType();
}