package net.sf.anathema.character.main.charm.combo;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.IExtendedCharmData;
import net.sf.anathema.character.main.magic.charms.type.CharmType;

public class SecondEditionComboArbitrator extends ComboArbitrator {

  @Override
  protected boolean isCharmLegalByRules(ICharm charm) {
    boolean comboBasic = isComboBasic(charm);
    boolean comboOk = charm.hasAttribute(IExtendedCharmData.COMBO_OK_ATTRIBUTE);
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
    return charm.hasAttribute(IExtendedCharmData.COMBO_BASIC_ATTRIBUTE);
  }
}