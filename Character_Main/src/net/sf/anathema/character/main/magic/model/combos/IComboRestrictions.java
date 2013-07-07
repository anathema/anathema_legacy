package net.sf.anathema.character.main.magic.model.combos;

import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.types.AbilityType;

public interface IComboRestrictions {

  boolean combosAllAbilities();
  
  boolean combosSelectAbility(AbilityType type);

  boolean isComboAllowed(boolean isAllowedByDefault);

  boolean isRestrictedCharm(ICharm charm);

  TraitType[] getRestrictedTraitTypes();
}