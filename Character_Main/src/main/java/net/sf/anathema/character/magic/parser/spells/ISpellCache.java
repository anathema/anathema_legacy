package net.sf.anathema.character.magic.parser.spells;

import net.sf.anathema.character.framework.data.ExtensibleDataSet;
import net.sf.anathema.character.magic.spells.Spell;

public interface ISpellCache extends ExtensibleDataSet {
  Spell[] getSpells();
}
