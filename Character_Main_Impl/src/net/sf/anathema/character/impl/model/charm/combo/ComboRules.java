package net.sf.anathema.character.impl.model.charm.combo;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.CharmType;
import net.sf.anathema.character.generic.magic.charms.DurationType;
import net.sf.anathema.character.generic.magic.charms.ICharmTypeVisitor;
import net.sf.anathema.character.generic.magic.charms.IComboRestrictions;
import net.sf.anathema.lib.lang.ArrayUtilities;

public class ComboRules implements IComboRules {

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
    boolean isLegalDuration = charm.getDuration().getType() == DurationType.Instant;
    return charm.getComboRules().isComboAllowed(isLegalDuration);
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

  private boolean specialRestrictionsApply(ICharm charm1, ICharm charm2) {
    IComboRestrictions comboRules = charm1.getComboRules();
    if (comboRules.isRestrictedCharm(charm2)) {
      return true;
    }
    if (ArrayUtilities.contains(comboRules.getRestrictedTraitTypes(), charm2.getPrerequisites()[0].getType())) {
      return true;
    }
    return false;
  }

  private boolean handleComboRules(final ICharm charm1, final ICharm charm2) {
    final boolean[] legal = new boolean[1];
    charm1.getCharmType().accept(new ICharmTypeVisitor() {
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

      public void visitSpecial(CharmType visitedType) {
        legal[0] = false;
      }
    });
    return legal[0];
  }
}