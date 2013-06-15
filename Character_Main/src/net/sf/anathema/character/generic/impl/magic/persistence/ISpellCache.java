package net.sf.anathema.character.generic.impl.magic.persistence;

import net.sf.anathema.character.generic.data.IExtensibleDataSet;
import net.sf.anathema.character.generic.magic.ISpell;

public interface ISpellCache extends IExtensibleDataSet {
  ISpell[] getSpells();
}
