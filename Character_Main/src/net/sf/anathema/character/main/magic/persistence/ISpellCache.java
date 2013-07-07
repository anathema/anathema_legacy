package net.sf.anathema.character.main.magic.persistence;

import net.sf.anathema.character.main.data.IExtensibleDataSet;
import net.sf.anathema.character.main.magic.ISpell;

public interface ISpellCache extends IExtensibleDataSet {
  ISpell[] getSpells();
}
