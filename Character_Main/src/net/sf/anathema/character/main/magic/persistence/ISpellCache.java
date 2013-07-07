package net.sf.anathema.character.main.magic.persistence;

import net.sf.anathema.character.main.framework.data.IExtensibleDataSet;
import net.sf.anathema.character.main.magic.model.spells.ISpell;

public interface ISpellCache extends IExtensibleDataSet {
  ISpell[] getSpells();
}
