package net.sf.anathema.hero.spells.model;

import net.sf.anathema.character.main.magic.model.spells.ISpell;

import java.util.List;

public class CreationSpellLearnStrategy implements ISpellLearnStrategy {

  @Override
  public void addSpells(SpellsModel configuration, List<ISpell> addedSpells) {
    configuration.addSpells(addedSpells, false);
  }

  @Override
  public void removeSpells(SpellsModel configuration, List<ISpell> removedSpells) {
    configuration.removeSpells(removedSpells, false);
  }

  @Override
  public boolean isSpellAllowed(SpellsModel configuration, ISpell spell) {
    return configuration.isSpellAllowed(spell, false);
  }

  @Override
  public ISpell[] getLearnedSpells(SpellsModel configuration) {
    return configuration.getLearnedSpells(false);
  }

  @Override
  public boolean isLearned(SpellsModel configuration, ISpell spell) {
    return configuration.isLearnedOnCreation(spell);
  }
}