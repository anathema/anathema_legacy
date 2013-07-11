package net.sf.anathema.hero.spells.model;

import net.sf.anathema.character.main.magic.model.spells.ISpell;
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