package net.sf.anathema.character.main.magic.charm.combos;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.types.AbilityType;

public interface IComboRestrictions {

  boolean combosAllAbilities();
  
  boolean combosSelectAbility(AbilityType type);

  boolean isRestrictedCharm(Charm charm);

  TraitType[] getRestrictedTraitTypes();
}