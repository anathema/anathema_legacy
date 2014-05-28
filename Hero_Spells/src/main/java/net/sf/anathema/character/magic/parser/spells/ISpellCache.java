package net.sf.anathema.character.magic.parser.spells;

import net.sf.anathema.character.magic.spells.Spell;
import net.sf.anathema.hero.framework.data.ExtensibleDataSet;

public interface ISpellCache extends ExtensibleDataSet {
  Spell[] getSpells();
}
