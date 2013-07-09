package net.sf.anathema.character.main.magic.model.combos;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.character.main.traits.types.AttributeType;

public abstract class AbstractComboRules implements IComboRules {

  protected final boolean haveSamePrerequisite(Charm charm1, Charm charm2) {
    return charm1.getPrimaryTraitType() == charm2.getPrimaryTraitType();
  }

  protected final boolean haveAttributePrerequisites(Charm charm1, Charm charm2) {
    return hasAttributePrerequisite(charm1) && hasAttributePrerequisite(charm2);
  }

  protected final boolean haveAbilityPrerequisites(Charm charm1, Charm charm2) {
    return hasAbilityPrerequisite(charm1) && hasAbilityPrerequisite(charm2);
  }

  protected final boolean hasNoTraitPrerequisites(Charm charm) {
    return !hasAttributePrerequisite(charm) && !hasAbilityPrerequisite(charm);
  }

  protected final boolean allAbilitiesRuleApplied(Charm charm1, Charm charm2) {
    return allAbiltiesCombo(charm1, charm2) || allAbiltiesCombo(charm2, charm1);
  }

  protected final boolean selectAbilitiesRuleApplied(Charm charm1, Charm charm2) {
    return selectAbilitiesCombo(charm1, charm2) || selectAbilitiesCombo(charm2, charm1);
  }

  protected final boolean isAbilityAttributeCombo(Charm charm1, Charm charm2) {
    return isAbilityAttributeMix(charm1, charm2) || isAbilityAttributeMix(charm2, charm1);
  }

  private boolean isAbilityAttributeMix(Charm charm1, Charm charm2) {
    return hasAbilityPrerequisite(charm1) && hasAttributePrerequisite(charm2);
  }

  private boolean hasAbilityPrerequisite(Charm charm) {
    return charm.getPrimaryTraitType() instanceof AbilityType;
  }

  private boolean hasAttributePrerequisite(Charm charm) {
    return charm.getPrimaryTraitType() instanceof AttributeType;
  }

  private boolean allAbiltiesCombo(Charm charm1, Charm charm2) {
    return charm1.getComboRules().combosAllAbilities() && hasAbilityPrerequisite(charm2);
  }

  private boolean selectAbilitiesCombo(Charm charm1, Charm charm2) {
    return hasAbilityPrerequisite(charm2) && charm1.getComboRules().combosSelectAbility((AbilityType) charm2.getPrimaryTraitType());
  }
}