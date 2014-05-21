package net.sf.anathema.character.magic.parser.spells;

import net.sf.anathema.character.magic.spells.Spell;

import java.util.ArrayList;
import java.util.List;

public class SpellCache implements ISpellCache {
  private List<Spell> spellList = new ArrayList<>();

  public void addSpell(Spell spell) {
    spellList.add(spell);
  }

  @Override
  public Spell[] getSpells() {
    return spellList.toArray(new Spell[0]);
  }
}
