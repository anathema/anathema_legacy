package net.sf.anathema.character.main.presenter.magic.combo;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.main.model.combos.CombosModel;
import net.sf.anathema.lib.gui.list.LegalityCheck;

public class ComboLegality implements LegalityCheck {
  private final CombosModel comboConfiguration;

  public ComboLegality(CombosModel comboConfiguration) {
    this.comboConfiguration = comboConfiguration;
  }

  @Override
  public boolean isLegal(Object object) {
    return comboConfiguration.isComboLegal((ICharm) object);
  }
}