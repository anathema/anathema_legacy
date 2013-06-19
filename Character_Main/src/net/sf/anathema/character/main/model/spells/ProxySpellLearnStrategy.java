package net.sf.anathema.character.main.model.spells;

import net.sf.anathema.character.generic.magic.ISpell;

public class ProxySpellLearnStrategy implements ISpellLearnStrategy {

  private ISpellLearnStrategy strategy;

  public ProxySpellLearnStrategy(ISpellLearnStrategy strategy) {
    this.strategy = strategy;
  }

  public void setStrategy(ISpellLearnStrategy strategy) {
    this.strategy = strategy;
  }

  @Override
  public void addSpells(SpellModel configuration, ISpell[] addedSpells) {
    strategy.addSpells(configuration, addedSpells);
  }

  @Override
  public void removeSpells(SpellModel configuration, ISpell[] removedSpells) {
    strategy.removeSpells(configuration, removedSpells);
  }

  @Override
  public boolean isSpellAllowed(SpellModel configuration, ISpell spell) {
    return strategy.isSpellAllowed(configuration, spell);
  }

  @Override
  public ISpell[] getLearnedSpells(SpellModel configuration) {
    return strategy.getLearnedSpells(configuration);
  }

  @Override
  public boolean isLearned(SpellModel configuration, ISpell spell) {
    return strategy.isLearned(configuration, spell);
  }
}