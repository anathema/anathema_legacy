package net.sf.anathema.character.impl.model.charm.combo;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.CharmType;
import net.sf.anathema.character.generic.magic.charms.DurationType;
import net.sf.anathema.character.generic.magic.charms.ICharmTypeVisitor;

public class ComboRules extends AbstractComboRules {

  private final IComboRules simpleCharmRules = new SimpleCharmComboRules();
  private final IComboRules extraActionCharmRules = new ExtraActionCharmComboRules();
  private final IComboRules supplementalCharmRules = new SupplementalCharmComboRules();

  public boolean isCharmComboLegal(ICharm charm) {
    boolean isLegalDuration = charm.getDuration().getType() == DurationType.Instant;
    return charm.getComboRules().isComboAllowed(isLegalDuration);
  }

  public boolean isComboLegal(final ICharm charm1, final ICharm charm2) {
    if (charm1 == charm2) {
      return false;
    }
    if (charm1.getComboRules().isRestrictedCharm(charm2) || charm2.getComboRules().isRestrictedCharm(charm1)) {
      return false;
    }
    return handleComboRules(charm1, charm2) && handleComboRules(charm2, charm1);
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
        legal[0] = true;
      }

      public void visitSupplemental(CharmType visitedType) {
        legal[0] = supplementalCharmRules.isComboLegal(charm1, charm2);
      }

      public void visitSpecial(CharmType visitedType) {
        legal[0] = true;
      }
    });
    return legal[0];
  }
}