package net.sf.anathema.character.impl.model.charm.combo;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;

public abstract class AbstractComboRules implements IComboRules {

  protected final boolean haveSamePrerequisite(ICharm charm1, ICharm charm2) {
    return charm1.getPrerequisites()[0].getType() == charm2.getPrerequisites()[0].getType();
  }

  protected final boolean haveAttributePrerequisites(ICharm charm1, ICharm charm2) {
    return hasAttributePrerequisite(charm1) && hasAttributePrerequisite(charm2);
  }

  protected final boolean haveAbilityPrerequisites(ICharm charm1, ICharm charm2) {
    return hasAbilityPrerequisite(charm1) && hasAbilityPrerequisite(charm2);
  }

  protected boolean hasAbilityPrerequisite(ICharm charm) {
    return charm.getPrerequisites()[0].getType() instanceof AbilityType;
  }

  private boolean hasAttributePrerequisite(ICharm charm) {
    return charm.getPrerequisites()[0].getType() instanceof AttributeType;
  }

  protected boolean allAbilitiesRuleApplied(ICharm charm1, ICharm charm2) {
    return (allAbiltiesCombo(charm1, charm2)) || (allAbiltiesCombo(charm2, charm1));
  }

  private boolean allAbiltiesCombo(ICharm charm1, ICharm charm2) {
    return charm1.getComboRules().combosAllAbilities() && hasAbilityPrerequisite(charm2);
  }
}