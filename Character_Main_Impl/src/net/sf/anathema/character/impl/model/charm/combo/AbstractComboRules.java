package net.sf.anathema.character.impl.model.charm.combo;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;

public abstract class AbstractComboRules implements IComboRules {

  protected final boolean haveSamePrerequisite(ICharm charm1, ICharm charm2) {
    return charm1.getPrimaryTraitType() == charm2.getPrimaryTraitType();
  }

  protected final boolean haveAttributePrerequisites(ICharm charm1, ICharm charm2) {
    return hasAttributePrerequisite(charm1) && hasAttributePrerequisite(charm2);
  }

  protected final boolean haveAbilityPrerequisites(ICharm charm1, ICharm charm2) {
    return hasAbilityPrerequisite(charm1) && hasAbilityPrerequisite(charm2);
  }

  protected final boolean allAbilitiesRuleApplied(ICharm charm1, ICharm charm2) {
    return allAbiltiesCombo(charm1, charm2) || allAbiltiesCombo(charm2, charm1);
  }
  
  protected final boolean selectAbilitiesRuleApplied(ICharm charm1, ICharm charm2)
  {
	return selectAbilitiesCombo(charm1, charm2) || selectAbilitiesCombo(charm2, charm1);
  }

  protected final boolean isAbilityAttributeCombo(ICharm charm1, ICharm charm2) {
    return isAbilityAttributeMix(charm1, charm2) || isAbilityAttributeMix(charm2, charm1);
  }

  private boolean isAbilityAttributeMix(ICharm charm1, ICharm charm2) {
    return hasAbilityPrerequisite(charm1) && hasAttributePrerequisite(charm2);
  }

  private boolean hasAbilityPrerequisite(ICharm charm) {
    return charm.getPrimaryTraitType() instanceof AbilityType;
  }

  private boolean hasAttributePrerequisite(ICharm charm) {
    return charm.getPrimaryTraitType() instanceof AttributeType;
  }

  private boolean allAbiltiesCombo(ICharm charm1, ICharm charm2) {
    return charm1.getComboRules().combosAllAbilities() && hasAbilityPrerequisite(charm2);
  }
  
  private boolean selectAbilitiesCombo(ICharm charm1, ICharm charm2)
  {
	return hasAbilityPrerequisite(charm2) && charm1.getComboRules().combosSelectAbility((AbilityType) charm2.getPrimaryTraitType()); 
  }
}