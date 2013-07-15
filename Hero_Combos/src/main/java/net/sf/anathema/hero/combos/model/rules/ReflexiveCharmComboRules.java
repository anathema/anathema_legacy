package net.sf.anathema.hero.combos.model.rules;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.ICharmTypeVisitor;
import net.sf.anathema.character.main.magic.model.charm.type.CharmType;

public class ReflexiveCharmComboRules extends AbstractComboRules {
  private boolean crossPrerequisite;

  @Override
  public void setCrossPrerequisiteTypeComboAllowed(boolean allowed) {
    this.crossPrerequisite = allowed;
  }

  @Override
  public boolean isComboLegal(final Charm reflexiveCharm, final Charm otherCharm) {
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