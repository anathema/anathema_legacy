package net.sf.anathema.hero.spells.model;

import net.sf.anathema.character.main.magic.model.spells.ISpell;

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
  public void addSpells(SpellsModel configuration, List<ISpell> addedSpells) {
    strategy.addSpells(configuration, addedSpells);
  }

  @Override
  public void removeSpells(SpellsModel configuration, List<ISpell> removedSpells) {
    strategy.removeSpells(configuration, removedSpells);
  }

  @Override
  public boolean isSpellAllowed(SpellsModel configuration, ISpell spell) {
    return strategy.isSpellAllowed(configuration, spell);
  }

  @Override
  public ISpell[] getLearnedSpells(SpellsModel configuration) {
    return strategy.getLearnedSpells(configuration);
  }

  @Override
  public boolean isLearned(SpellsModel configuration, ISpell spell) {
    return strategy.isLearned(configuration, spell);
  }
}