package net.sf.anathema.hero.abilities.model;

import net.sf.anathema.character.main.template.abilities.AbilityGroupType;
import net.sf.anathema.hero.traits.model.group.AbstractTraitTypeGroupFactory;
import net.sf.anathema.hero.concept.CasteCollection;
import net.sf.anathema.lib.util.Identifier;

public class AbilityTypeGroupFactory extends AbstractTraitTypeGroupFactory {

  @Override
  protected Identifier getGroupIdentifier(CasteCollection casteCollection, String groupId) {
    return casteCollection.containsCasteType(groupId) ? casteCollection.getById(groupId) : AbilityGroupType.valueOf(groupId);
  }
}