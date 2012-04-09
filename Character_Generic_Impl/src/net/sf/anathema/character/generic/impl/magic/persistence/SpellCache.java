package net.sf.anathema.character.generic.impl.magic.persistence;

import java.util.List;
import java.util.ArrayList;

import net.sf.anathema.character.generic.magic.ISpell;

public class SpellCache implements ISpellCache {
	private static final SpellCache instance = new SpellCache();
	private List<ISpell> spellList = new ArrayList<ISpell>();
	
	public static SpellCache getInstance() {
		return instance;
	}
	
	public void addSpell(ISpell spell) {
		spellList.add(spell);
	}

	@Override
	public ISpell[] getSpells() {
		return spellList.toArray(new ISpell[0]);
	}

}
