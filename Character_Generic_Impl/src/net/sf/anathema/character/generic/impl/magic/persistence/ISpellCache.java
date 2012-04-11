package net.sf.anathema.character.generic.impl.magic.persistence;

import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.lib.resources.IExtensibleDataSet;

public interface ISpellCache extends IExtensibleDataSet {
	ISpell[] getSpells();
}
