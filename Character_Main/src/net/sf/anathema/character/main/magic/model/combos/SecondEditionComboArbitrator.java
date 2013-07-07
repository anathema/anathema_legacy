package net.sf.anathema.character.main.magic.model.combos;

import net.sf.anathema.character.main.magic.model.charm.CharmAttributeList;
import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.model.charm.type.CharmType;

public class SecondEditionComboArbitrator extends ComboArbitrator {

  @Override
  protected boolean isCharmLegalByRules(ICharm charm) {
    boolean comboBasic = isComboBasic(charm);
    boolean comboOk = charm.hasAttribute(CharmAttributeList.COMBO_OK_ATTRIBUTE);
    return comboBasic || comboOk;
  }

  @Override
  protected boolean specialRestrictionsApply(ICharm charm1, ICharm charm2) {
    if (isComboBasic(charm1) && charm2.getCharmTypeModel().getCharmType() != CharmType.Reflexive) {
      return true;
    }
    return super.specialRestrictionsApply(charm1, charm2);
  }

  private boolean isComboBasic(ICharm charm) {
    return charm.hasAttribute(CharmAttributeList.COMBO_BASIC_ATTRIBUTE);
  }
}