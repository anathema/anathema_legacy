package net.sf.anathema.hero.combos.display;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.hero.combos.CombosModel;
import net.sf.anathema.lib.gui.list.LegalityCheck;

public class ComboLegality implements LegalityCheck {
  private final CombosModel comboConfiguration;

  public ComboLegality(CombosModel comboConfiguration) {
    this.comboConfiguration = comboConfiguration;
  }

  @Override
  public boolean isLegal(Object object) {
    return comboConfiguration.isComboLegal((Charm) object);
  }
}