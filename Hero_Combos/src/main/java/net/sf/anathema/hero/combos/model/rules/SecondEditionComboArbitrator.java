package net.sf.anathema.hero.combos.model.rules;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.CharmAttributeList;
import net.sf.anathema.hero.charmtree.type.CharmType;

public class SecondEditionComboArbitrator extends AbstractComboArbitrator {

  @Override
  protected boolean isCharmLegalByRules(Charm charm) {
    boolean comboBasic = isComboBasic(charm);
    boolean comboOk = charm.hasAttribute(CharmAttributeList.COMBO_OK_ATTRIBUTE);
    return comboBasic || comboOk;
  }

  @SuppressWarnings("SimplifiableIfStatement")
  @Override
  protected boolean specialRestrictionsApply(Charm charm1, Charm charm2) {
    if (isComboBasic(charm1) && charm2.getCharmTypeModel().getCharmType() != CharmType.Reflexive) {
      return true;
    }
    return super.specialRestrictionsApply(charm1, charm2);
  }

  private boolean isComboBasic(Charm charm) {
    return charm.hasAttribute(CharmAttributeList.COMBO_BASIC_ATTRIBUTE);
  }
}