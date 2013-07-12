package net.sf.anathema.hero.spells.model;

import net.sf.anathema.character.main.magic.model.spells.Spell;

import java.util.List;

public class CreationSpellLearnStrategy implements ISpellLearnStrategy {

  @Override
  public void addSpells(SpellsModel configuration, List<Spell> addedSpells) {
    configuration.addSpells(addedSpells, false);
  }

  @Override
  public void removeSpells(SpellsModel configuration, List<Spell> removedSpells) {
    configuration.removeSpells(removedSpells, false);
  }

  @Override
  public boolean isSpellAllowed(SpellsModel configuration, Spell spell) {
    return configuration.isSpellAllowed(spell, false);
  }

  @Override
  public Spell[] getLearnedSpells(SpellsModel configuration) {
    return configuration.getLearnedSpells(false);
  }

  @Override
  public boolean isLearned(SpellsModel configuration, Spell spell) {
    return configuration.isLearnedOnCreation(spell);
  }
}