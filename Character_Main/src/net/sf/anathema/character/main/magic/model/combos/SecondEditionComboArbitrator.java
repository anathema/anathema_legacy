package net.sf.anathema.character.main.magic.model.combos;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.CharmAttributeList;
import net.sf.anathema.character.main.magic.model.charm.type.CharmType;

public class SecondEditionComboArbitrator extends ComboArbitrator {

  @Override
  protected boolean isCharmLegalByRules(Charm charm) {
    boolean comboBasic = isComboBasic(charm);
    boolean comboOk = charm.hasAttribute(CharmAttributeList.COMBO_OK_ATTRIBUTE);
    return comboBasic || comboOk;
  }

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