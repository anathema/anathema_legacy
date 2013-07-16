package net.sf.anathema.hero.spells.model;

import net.sf.anathema.character.main.magic.spells.Spell;
import net.sf.anathema.lib.gui.list.LegalityCheck;

public class SpellLegality implements LegalityCheck {
  private final SpellsModel spellConfiguration;

  public SpellLegality(SpellsModel spellConfiguration) {
    this.spellConfiguration = spellConfiguration;
  }

  @Override
  public boolean isLegal(Object object) {
    return spellConfiguration.isSpellAllowed((Spell) object);
  }
}