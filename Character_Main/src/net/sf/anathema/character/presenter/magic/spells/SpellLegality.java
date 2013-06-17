package net.sf.anathema.character.presenter.magic.spells;

import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.model.ISpellConfiguration;
import net.sf.anathema.lib.gui.list.LegalityCheck;

public class SpellLegality implements LegalityCheck {
  private final ISpellConfiguration spellConfiguration;

  public SpellLegality(ISpellConfiguration spellConfiguration) {
    this.spellConfiguration = spellConfiguration;
  }

  @Override
  public boolean isLegal(Object object) {
    return spellConfiguration.isSpellAllowed((ISpell) object);
  }
}