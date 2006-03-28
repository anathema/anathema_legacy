package net.sf.anathema.character.impl.model.charm.combo;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.CharmType;
import net.sf.anathema.character.generic.magic.charms.ICharmTypeVisitor;

public class ReflexiveCharmComboRules extends AbstractComboRules {
  private boolean crossPrerequisite;

  public void setCrossPrerequisiteTypeComboAllowed(boolean allowed) {
    this.crossPrerequisite = allowed;
  }

  public boolean isComboLegal(final ICharm reflexiveCharm, final ICharm otherCharm) {
    final boolean[] legal = new boolean[1];
    otherCharm.getCharmType().accept(new ICharmTypeVisitor() {
      public void visitSimple(CharmType visitedType) {
        legal[0] = haveAbilityPrerequisites(reflexiveCharm, otherCharm)
            || haveAttributePrerequisites(reflexiveCharm, otherCharm)
            || crossPrerequisite;
      }

      public void visitExtraAction(CharmType visitedType) {
        legal[0] = haveAbilityPrerequisites(reflexiveCharm, otherCharm)
            || haveAttributePrerequisites(reflexiveCharm, otherCharm)
            || crossPrerequisite;
      }

      public void visitReflexive(CharmType visitedType) {
        legal[0] = haveAbilityPrerequisites(reflexiveCharm, otherCharm)
            || haveAttributePrerequisites(reflexiveCharm, otherCharm)
            || crossPrerequisite;
      }

      public void visitSupplemental(CharmType visitedType) {
        legal[0] = haveAbilityPrerequisites(reflexiveCharm, otherCharm)
            || haveAttributePrerequisites(reflexiveCharm, otherCharm)
            || crossPrerequisite;
      }

      public void visitSpecial(CharmType visitedType) {
        legal[0] = false;
      }
    });
    return legal[0];
  }
}