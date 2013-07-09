package net.sf.anathema.character.main.magic.model.combos;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.ICharmTypeVisitor;
import net.sf.anathema.character.main.magic.model.charm.type.CharmType;

public class ExtraActionCharmComboRules extends AbstractComboRules {

  private boolean crossPrerequisite;

  @Override
  public void setCrossPrerequisiteTypeComboAllowed(boolean allowed) {
    this.crossPrerequisite = allowed;
  }

  @Override
  public boolean isComboLegal(final Charm extraActionCharm, final Charm otherCharm) {
    final boolean[] legal = new boolean[1];
    otherCharm.getCharmTypeModel().getCharmType().accept(new ICharmTypeVisitor() {
      @Override
      public void visitSimple(CharmType visitedType) {
        boolean allAbilitiesRule = allAbilitiesRuleApplied(extraActionCharm, otherCharm);
        boolean selectAbilitiesRule = selectAbilitiesRuleApplied(extraActionCharm, otherCharm);
        boolean samePrerequisite = haveSamePrerequisite(extraActionCharm, otherCharm);
        boolean attributePrerequisites = haveAttributePrerequisites(extraActionCharm, otherCharm);
        boolean abilityAttributeCombo = crossPrerequisite && isAbilityAttributeCombo(extraActionCharm, otherCharm);
        boolean noTraitPrerequisiteCombo = hasNoTraitPrerequisites(extraActionCharm);
        legal[0] = allAbilitiesRule || selectAbilitiesRule || samePrerequisite || attributePrerequisites || abilityAttributeCombo ||
                   noTraitPrerequisiteCombo;
      }

      @Override
      public void visitExtraAction(CharmType visitedType) {
        legal[0] = false;
      }

      @Override
      public void visitReflexive(CharmType visitedType) {
        legal[0] = true;
      }

      @Override
      public void visitSupplemental(CharmType visitedType) {
        boolean allAbilitiesRule = allAbilitiesRuleApplied(extraActionCharm, otherCharm);
        boolean selectAbilitiesRule = selectAbilitiesRuleApplied(extraActionCharm, otherCharm);
        boolean samePrerequisite = haveSamePrerequisite(extraActionCharm, otherCharm);
        boolean attributePrerequisites = haveAttributePrerequisites(extraActionCharm, otherCharm);
        boolean abilityAttributeCombo = crossPrerequisite && isAbilityAttributeCombo(extraActionCharm, otherCharm);
        boolean noTraitPrerequisiteCombo = hasNoTraitPrerequisites(extraActionCharm);
        legal[0] = allAbilitiesRule || selectAbilitiesRule || samePrerequisite || attributePrerequisites || abilityAttributeCombo ||
                   noTraitPrerequisiteCombo;
      }

      @Override
      public void visitPermanent(CharmType visitedType) {
        legal[0] = false;
      }

      @Override
      public void visitSpecial(CharmType visitedType) {
        legal[0] = false;
      }
    });
    return legal[0];
  }
}