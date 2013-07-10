package net.sf.anathema.character.main.traits.creation;

import net.sf.anathema.character.main.caste.CasteCollection;
import net.sf.anathema.character.main.traits.types.AttributeGroupType;
import net.sf.anathema.lib.util.Identifier;

public class AttributeTypeGroupFactory extends AbstractTraitTypeGroupFactory {

  @Override
  protected Identifier getGroupIdentifier(CasteCollection casteCollection, String groupId) {
    return AttributeGroupType.valueOf(groupId);
  }
}