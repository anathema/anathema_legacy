package net.sf.anathema.hero.spells.model;

import net.sf.anathema.character.main.magic.model.spells.ISpell;
import net.sf.anathema.hero.spells.SpellModel;

import java.util.List;

public class CreationSpellLearnStrategy implements ISpellLearnStrategy {

  @Override
  public void addSpells(SpellModel configuration, List<ISpell> addedSpells) {
    configuration.addSpells(addedSpells, false);
  }

  @Override
  public void removeSpells(SpellModel configuration, List<ISpell> removedSpells) {
    configuration.removeSpells(removedSpells, false);
  }

  @Override
  public boolean isSpellAllowed(SpellModel configuration, ISpell spell) {
    return configuration.isSpellAllowed(spell, false);
  }

  @Override
  public ISpell[] getLearnedSpells(SpellModel configuration) {
    return configuration.getLearnedSpells(false);
  }

  @Override
  public boolean isLearned(SpellModel configuration, ISpell spell) {
    return configuration.isLearnedOnCreation(spell);
  }
}