package net.sf.anathema.character.impl.model.charm.combo;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmTypeVisitor;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;

public class ExtraActionCharmComboRules extends AbstractComboRules {

  private boolean crossPrerequisite;

  public void setCrossPrerequisiteTypeComboAllowed(boolean allowed) {
    this.crossPrerequisite = allowed;
  }

  public boolean isComboLegal(final ICharm extraActionCharm, final ICharm otherCharm) {
    final boolean[] legal = new boolean[1];
    otherCharm.getCharmTypeModel().getCharmType().accept(new ICharmTypeVisitor() {
      public void visitSimple(CharmType visitedType) {
        boolean allAbilitiesRule = allAbilitiesRuleApplied(extraActionCharm, otherCharm);
        boolean samePrerequisite = haveSamePrerequisite(extraActionCharm, otherCharm);
        boolean attributePrerequisites = haveAttributePrerequisites(extraActionCharm, otherCharm);
        boolean abilityAttributeCombo = crossPrerequisite && isAbilityAttributeCombo(extraActionCharm, otherCharm);
        legal[0] = allAbilitiesRule || samePrerequisite || attributePrerequisites || abilityAttributeCombo;
      }

      public void visitExtraAction(CharmType visitedType) {
        legal[0] = false;
      }

      public void visitReflexive(CharmType visitedType) {
        legal[0] = true;
      }

      public void visitSupplemental(CharmType visitedType) {
        boolean allAbilitiesRule = allAbilitiesRuleApplied(extraActionCharm, otherCharm);
        boolean samePrerequisite = haveSamePrerequisite(extraActionCharm, otherCharm);
        boolean attributePrerequisites = haveAttributePrerequisites(extraActionCharm, otherCharm);
        boolean abilityAttributeCombo = crossPrerequisite && isAbilityAttributeCombo(extraActionCharm, otherCharm);
        legal[0] = allAbilitiesRule || samePrerequisite || attributePrerequisites || abilityAttributeCombo;
      }

      public void visitSpecial(CharmType visitedType) {
        legal[0] = false;
      }
    });
    return legal[0];
  }
}