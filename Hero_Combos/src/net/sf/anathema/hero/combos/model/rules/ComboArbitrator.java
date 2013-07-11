package net.sf.anathema.hero.combos.model.rules;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.ICharmTypeVisitor;
import net.sf.anathema.character.main.magic.model.charm.type.CharmType;
import net.sf.anathema.hero.combos.display.presenter.ICombo;
import net.sf.anathema.hero.combos.model.IComboArbitrator;
import net.sf.anathema.character.main.magic.model.combos.IComboRestrictions;
import net.sf.anathema.hero.combos.model.IComboRules;
import org.apache.commons.lang3.ArrayUtils;

public abstract class ComboArbitrator implements IComboArbitrator {

  private final IComboRules simpleCharmRules = new SimpleCharmComboRules();
  private final IComboRules extraActionCharmRules = new ExtraActionCharmComboRules();
  private final IComboRules supplementalCharmRules = new SupplementalCharmComboRules();
  private final IComboRules reflexiveCharmRules = new ReflexiveCharmComboRules();

  @Override
  public void setCrossPrerequisiteTypeComboAllowed(boolean allowed) {
    simpleCharmRules.setCrossPrerequisiteTypeComboAllowed(allowed);
    extraActionCharmRules.setCrossPrerequisiteTypeComboAllowed(allowed);
    supplementalCharmRules.setCrossPrerequisiteTypeComboAllowed(allowed);
    reflexiveCharmRules.setCrossPrerequisiteTypeComboAllowed(allowed);
  }

  public boolean isCharmComboLegal(Charm charm) {
    return isCharmLegalByRules(charm);
  }

  protected abstract boolean isCharmLegalByRules(Charm charm);

  @Override
  public boolean canBeAddedToCombo(ICombo combo, Charm charm) {
    boolean legal = isCharmComboLegal(charm);
    for (Charm comboCharm : combo.getCharms()) {
      legal = legal && isComboLegal(comboCharm, charm);
    }
    return legal;
  }

  @Override
  public boolean isComboLegal(Charm charm1, Charm charm2) {
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

  protected boolean specialRestrictionsApply(Charm charm1, Charm charm2) {
    IComboRestrictions comboRules = charm1.getComboRules();
    return comboRules.isRestrictedCharm(charm2) || ArrayUtils.contains(comboRules.getRestrictedTraitTypes(),
            charm2.getPrimaryTraitType());
  }

  private boolean handleComboRules(final Charm charm1, final Charm charm2) {
    final boolean[] legal = new boolean[1];
    charm1.getCharmTypeModel().getCharmType().accept(new ICharmTypeVisitor() {
      @Override
      public void visitSimple(CharmType visitedType) {
        legal[0] = simpleCharmRules.isComboLegal(charm1, charm2);
      }

      @Override
      public void visitExtraAction(CharmType visitedType) {
        legal[0] = extraActionCharmRules.isComboLegal(charm1, charm2);
      }

      @Override
      public void visitReflexive(CharmType visitedType) {
        legal[0] = reflexiveCharmRules.isComboLegal(charm1, charm2);
      }

      @Override
      public void visitSupplemental(CharmType visitedType) {
        legal[0] = supplementalCharmRules.isComboLegal(charm1, charm2);
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