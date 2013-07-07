package net.sf.anathema.character.main.magic.persistence;

import net.sf.anathema.character.main.magic.model.spells.ISpell;

import java.util.ArrayList;
import java.util.List;

public class SpellCache implements ISpellCache {
  private List<ISpell> spellList = new ArrayList<>();

  public void addSpell(ISpell spell) {
    spellList.add(spell);
  }

  @Override
  public ISpell[] getSpells() {
    return spellList.toArray(new ISpell[0]);
  }
}
