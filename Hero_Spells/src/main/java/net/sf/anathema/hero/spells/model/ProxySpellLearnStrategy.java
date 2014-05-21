package net.sf.anathema.hero.spells.model;

import net.sf.anathema.character.magic.spells.Spell;

import java.util.List;

public class ProxySpellLearnStrategy implements ISpellLearnStrategy {

  private ISpellLearnStrategy strategy;

  public ProxySpellLearnStrategy(ISpellLearnStrategy strategy) {
    this.strategy = strategy;
  }

  public void setStrategy(ISpellLearnStrategy strategy) {
    this.strategy = strategy;
  }

  @Override
  public void addSpells(SpellsModel configuration, List<Spell> addedSpells) {
    strategy.addSpells(configuration, addedSpells);
  }

  @Override
  public void removeSpells(SpellsModel configuration, List<Spell> removedSpells) {
    strategy.removeSpells(configuration, removedSpells);
  }

  @Override
  public boolean isSpellAllowed(SpellsModel configuration, Spell spell) {
    return strategy.isSpellAllowed(configuration, spell);
  }

  @Override
  public Spell[] getLearnedSpells(SpellsModel configuration) {
    return strategy.getLearnedSpells(configuration);
  }

  @Override
  public boolean isLearned(SpellsModel configuration, Spell spell) {
    return strategy.isLearned(configuration, spell);
  }
}