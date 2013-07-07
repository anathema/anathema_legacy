package net.sf.anathema.character.main.magic.charms.combo;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.ICharmTypeVisitor;
import net.sf.anathema.character.main.magic.charms.type.CharmType;

public class SupplementalCharmComboRules extends AbstractComboRules {
  private boolean crossPrerequisite;

  @Override
  public void setCrossPrerequisiteTypeComboAllowed(boolean allowed) {
    this.crossPrerequisite = allowed;
  }

  @Override
  public boolean isComboLegal(final ICharm supplementalCharm, final ICharm otherCharm) {
    final boolean[] legal = new boolean[1];
    otherCharm.getCharmTypeModel().getCharmType().accept(new ICharmTypeVisitor() {
      @Override
      public void visitSimple(CharmType visitedType) {
        boolean allAbilitiesRule = allAbilitiesRuleApplied(supplementalCharm, otherCharm);
        boolean selectAbilitiesRule = selectAbilitiesRuleApplied(supplementalCharm, otherCharm);
        boolean samePrerequisite = haveSamePrerequisite(supplementalCharm, otherCharm);
        boolean attributePrerequisites = haveAttributePrerequisites(supplementalCharm, otherCharm);
        boolean abilityAttributeCombo = crossPrerequisite && isAbilityAttributeCombo(supplementalCharm, otherCharm);
        boolean noTraitPrerequisiteCombo = hasNoTraitPrerequisites(supplementalCharm);
        legal[0] = allAbilitiesRule || selectAbilitiesRule || samePrerequisite || attributePrerequisites || abilityAttributeCombo ||
                   noTraitPrerequisiteCombo;
      }

      @Override
      public void visitExtraAction(CharmType visitedType) {
        boolean allAbilitiesRule = allAbilitiesRuleApplied(supplementalCharm, otherCharm);
        boolean selectAbilitiesRule = selectAbilitiesRuleApplied(supplementalCharm, otherCharm);
        boolean samePrerequisite = haveSamePrerequisite(supplementalCharm, otherCharm);
        boolean attributePrerequisites = haveAttributePrerequisites(supplementalCharm, otherCharm);
        boolean abilityAttributeCombo = crossPrerequisite && isAbilityAttributeCombo(supplementalCharm, otherCharm);
        boolean noTraitPrerequisiteCombo = hasNoTraitPrerequisites(supplementalCharm);
        legal[0] = allAbilitiesRule || selectAbilitiesRule || samePrerequisite || attributePrerequisites || abilityAttributeCombo ||
                   noTraitPrerequisiteCombo;
      }

      @Override
      public void visitReflexive(CharmType visitedType) {
        legal[0] = true;
      }

      @Override
      public void visitSupplemental(CharmType visitedType) {
        boolean allAbilitiesRule = allAbilitiesRuleApplied(supplementalCharm, otherCharm);
        boolean selectAbilitiesRule = selectAbilitiesRuleApplied(supplementalCharm, otherCharm);
        boolean samePrerequisite = haveSamePrerequisite(supplementalCharm, otherCharm);
        boolean attributePrerequisites = haveAttributePrerequisites(supplementalCharm, otherCharm);
        boolean abilityAttributeCombo = crossPrerequisite && isAbilityAttributeCombo(supplementalCharm, otherCharm);
        boolean noTraitPrerequisiteCombo = hasNoTraitPrerequisites(supplementalCharm);
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