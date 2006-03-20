package net.sf.anathema.character.impl.model.charm.combo;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.CharmType;
import net.sf.anathema.character.generic.magic.charms.DurationType;
import net.sf.anathema.character.generic.magic.charms.ICharmTypeVisitor;

public class ComboRules implements IComboRules {

  private final IComboRules simpleCharmRules = new SimpleCharmComboRules();

  public boolean isCharmComboLegal(ICharm charm) {
    boolean isLegalDuration = charm.getDuration().getType() == DurationType.Instant;
    return charm.getComboRules().isComboAllowed(isLegalDuration);
  }

  public boolean isComboLegal(final ICharm charm1, final ICharm charm2) {
    if (charm1 == charm2) {
      return false;
    }
    final boolean[] legal = new boolean[1];
    charm1.getCharmType().accept(new ICharmTypeVisitor() {
      public void visitSimple(CharmType visitedType) {
        legal[0] = simpleCharmRules.isComboLegal(charm1, charm2);
      }

      public void visitExtraAction(CharmType visitedType) {
        legal[0] = charm2.getCharmType() != visitedType;
      }

      public void visitReflexive(CharmType visitedType) {
        legal[0] = true;
      }

      public void visitSupplemental(CharmType visitedType) {
        legal[0] = true;
      }

      public void visitSpecial(CharmType visitedType) {
        legal[0] = true;
      }
    });
    return legal[0];
  }
}