package net.sf.anathema.character.impl.model.traits.creation;

import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.traits.types.YoziType;
import net.sf.anathema.lib.util.Identified;

public class YoziTypeGroupFactory extends AbstractTraitTypeGroupFactory {

  @Override
  protected Identified getGroupIdentifier(ICasteCollection casteCollection, String groupId) {
	  return YoziType.valueOf(groupId);
  }
}