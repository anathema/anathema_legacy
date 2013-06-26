package net.sf.anathema.character.model.charm.combo;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmTypeVisitor;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;

public class ReflexiveCharmComboRules extends AbstractComboRules {
  private boolean crossPrerequisite;

  @Override
  public void setCrossPrerequisiteTypeComboAllowed(boolean allowed) {
    this.crossPrerequisite = allowed;
  }

  @Override
  public boolean isComboLegal(final ICharm reflexiveCharm, final ICharm otherCharm) {
    final boolean[] legal = new boolean[1];
    otherCharm.getCharmTypeModel().getCharmType().accept(new ICharmTypeVisitor() {
      @Override
      public void visitSimple(CharmType visitedType) {
        legal[0] = haveAbilityPrerequisites(reflexiveCharm, otherCharm) || haveAttributePrerequisites(reflexiveCharm, otherCharm) ||
                   hasNoTraitPrerequisites(reflexiveCharm) || crossPrerequisite;
      }

      @Override
      public void visitExtraAction(CharmType visitedType) {
        legal[0] = haveAbilityPrerequisites(reflexiveCharm, otherCharm) || haveAttributePrerequisites(reflexiveCharm, otherCharm) ||
                   hasNoTraitPrerequisites(reflexiveCharm) || crossPrerequisite;
      }

      @Override
      public void visitReflexive(CharmType visitedType) {
        legal[0] = haveAbilityPrerequisites(reflexiveCharm, otherCharm) || haveAttributePrerequisites(reflexiveCharm, otherCharm) ||
                   hasNoTraitPrerequisites(reflexiveCharm) || crossPrerequisite;
      }

      @Override
      public void visitSupplemental(CharmType visitedType) {
        legal[0] = haveAbilityPrerequisites(reflexiveCharm, otherCharm) || haveAttributePrerequisites(reflexiveCharm, otherCharm) ||
                   hasNoTraitPrerequisites(reflexiveCharm) || crossPrerequisite;
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