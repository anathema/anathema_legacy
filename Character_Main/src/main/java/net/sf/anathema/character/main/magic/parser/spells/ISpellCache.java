package net.sf.anathema.character.main.magic.parser.spells;

import net.sf.anathema.character.main.framework.data.ExtensibleDataSet;
import net.sf.anathema.character.main.magic.model.spells.Spell;

public interface ISpellCache extends ExtensibleDataSet {
  Spell[] getSpells();
}
