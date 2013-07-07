package net.sf.anathema.character.main.presenter.magic.spells;

import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.main.model.spells.SpellModel;
import net.sf.anathema.lib.gui.list.LegalityCheck;

public class SpellLegality implements LegalityCheck {
  private final SpellModel spellConfiguration;

  public SpellLegality(SpellModel spellConfiguration) {
    this.spellConfiguration = spellConfiguration;
  }

  @Override
  public boolean isLegal(Object object) {
    return spellConfiguration.isSpellAllowed((ISpell) object);
  }
}