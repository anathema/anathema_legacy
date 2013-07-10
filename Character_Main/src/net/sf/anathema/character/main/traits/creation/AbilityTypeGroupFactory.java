package net.sf.anathema.character.main.traits.creation;

import net.sf.anathema.character.main.caste.CasteCollection;
import net.sf.anathema.character.main.template.abilities.AbilityGroupType;
import net.sf.anathema.lib.util.Identifier;

public class AbilityTypeGroupFactory extends AbstractTraitTypeGroupFactory {

  @Override
  protected Identifier getGroupIdentifier(CasteCollection casteCollection, String groupId) {
    return casteCollection.containsCasteType(groupId) ? casteCollection.getById(groupId) : AbilityGroupType.valueOf(groupId);
  }
}