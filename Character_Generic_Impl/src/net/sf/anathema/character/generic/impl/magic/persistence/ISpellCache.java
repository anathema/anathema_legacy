package net.sf.anathema.character.generic.impl.magic.persistence;

import net.sf.anathema.character.generic.magic.ISpell;

public interface ISpellCache {
	final static String DATASET_ID = "Spells";
	
	ISpell[] getSpells();
}
