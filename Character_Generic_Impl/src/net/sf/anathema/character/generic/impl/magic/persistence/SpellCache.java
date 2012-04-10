package net.sf.anathema.character.generic.impl.magic.persistence;

import java.util.List;
import java.util.ArrayList;

import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.lib.resources.IExtensibleDataSet;

public class SpellCache implements ISpellCache, IExtensibleDataSet {
	private List<ISpell> spellList = new ArrayList<ISpell>();
	
	public void addSpell(ISpell spell) {
		spellList.add(spell);
	}

	@Override
	public ISpell[] getSpells() {
		return spellList.toArray(new ISpell[0]);
	}
	
	@Override
	public String getId() {
		return DATASET_ID;
	}

}
