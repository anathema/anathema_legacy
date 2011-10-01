package net.sf.anathema.character.impl.model.charm.combo;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmTypeVisitor;
import net.sf.anathema.character.generic.magic.charms.IComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.model.charm.ICombo;

public abstract class ComboArbitrator implements IComboArbitrator {

  private final IComboRules simpleCharmRules = new SimpleCharmComboRules();
  private final IComboRules extraActionCharmRules = new ExtraActionCharmComboRules();
  private final IComboRules supplementalCharmRules = new SupplementalCharmComboRules();
  private final IComboRules reflexiveCharmRules = new ReflexiveCharmComboRules();

  public void setCrossPrerequisiteTypeComboAllowed(boolean allowed) {
    simpleCharmRules.setCrossPrerequisiteTypeComboAllowed(allowed);
    extraActionCharmRules.setCrossPrerequisiteTypeComboAllowed(allowed);
    supplementalCharmRules.setCrossPrerequisiteTypeComboAllowed(allowed);
    reflexiveCharmRules.setCrossPrerequisiteTypeComboAllowed(allowed);
  }

  public boolean isCharmComboLegal(ICharm charm) {
    boolean isLegal = isCharmLegalByRules(charm);
    return charm.getComboRules().isComboAllowed(isLegal);
  }

  protected abstract boolean isCharmLegalByRules(ICharm charm);

  public boolean canBeAddedToCombo(ICombo combo, ICharm charm) {
    boolean legal = isCharmComboLegal(charm);
    for (ICharm comboCharm : combo.getCharms()) {
      legal = legal && isComboLegal(comboCharm, charm);
    }
    return legal;
  }

  public boolean isComboLegal(final ICharm charm1, final ICharm charm2) {
    if (charm1 == charm2) {
      return false;
    }
    if (!isCharmComboLegal(charm1) || !isCharmComboLegal(charm2)) {
      return false;
    }
    if (specialRestrictionsApply(charm1, charm2) || specialRestrictionsApply(charm2, charm1)) {
      return false;
    }
    return handleComboRules(charm1, charm2) && handleComboRules(charm2, charm1);
  }

  protected boolean specialRestrictionsApply(ICharm charm1, ICharm charm2) {
    IComboRestrictions comboRules = charm1.getComboRules();
      return comboRules.isRestrictedCharm(charm2) || ArrayUtilities.contains(comboRules.getRestrictedTraitTypes(), charm2.getPrimaryTraitType());
  }

  private boolean handleComboRules(final ICharm charm1, final ICharm charm2) {
    final boolean[] legal = new boolean[1];
    charm1.getCharmTypeModel().getCharmType().accept(new ICharmTypeVisitor() {
      public void visitSimple(CharmType visitedType) {
        legal[0] = simpleCharmRules.isComboLegal(charm1, charm2);
      }

      public void visitExtraAction(CharmType visitedType) {
        legal[0] = extraActionCharmRules.isComboLegal(charm1, charm2);
      }

      public void visitReflexive(CharmType visitedType) {
        legal[0] = reflexiveCharmRules.isComboLegal(charm1, charm2);
      }

      public void visitSupplemental(CharmType visitedType) {
        legal[0] = supplementalCharmRules.isComboLegal(charm1, charm2);
      }

      public void visitPermanent(CharmType visitedType) {
        legal[0] = false;
      }

      public void visitSpecial(CharmType visitedType) {
        legal[0] = false;
      }
    });
    return legal[0];
  }
}