package net.sf.anathema.character.impl.model.traits.creation;

import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.lib.util.Identified;

public class AttributeTypeGroupFactory extends AbstractTraitTypeGroupFactory {

  @Override
  protected Identified getGroupIdentifier(ICasteCollection casteCollection, String groupId) {
    return AttributeGroupType.valueOf(groupId);
  }
}