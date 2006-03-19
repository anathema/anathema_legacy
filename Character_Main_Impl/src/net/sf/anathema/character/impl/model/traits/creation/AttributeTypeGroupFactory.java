package net.sf.anathema.character.impl.model.traits.creation;

import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.lib.util.IIdentificate;

public class AttributeTypeGroupFactory extends AbstractTraitTypeGroupFactory {

  @Override
  protected IIdentificate getGroupIdentifier(ICasteCollection casteCollection, String groupId) {
    return AttributeGroupType.valueOf(groupId);
  }
}