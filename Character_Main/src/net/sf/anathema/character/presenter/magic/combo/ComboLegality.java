package net.sf.anathema.character.presenter.magic.combo;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.lib.gui.list.LegalityCheck;

public class ComboLegality implements LegalityCheck {
  private final IComboConfiguration comboConfiguration;

  public ComboLegality(IComboConfiguration comboConfiguration) {
    this.comboConfiguration = comboConfiguration;
  }

  @Override
  public boolean isLegal(Object object) {
    return comboConfiguration.isComboLegal((ICharm) object);
  }
}