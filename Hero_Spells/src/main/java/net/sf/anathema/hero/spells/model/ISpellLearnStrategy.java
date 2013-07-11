package net.sf.anathema.hero.spells.model;

import net.sf.anathema.character.main.magic.model.spells.ISpell;
import net.sf.anathema.hero.spells.SpellModel;

import java.util.List;

public interface ISpellLearnStrategy {

  void addSpells(SpellModel configuration, List<ISpell> addedSpells);

  void removeSpells(SpellModel configuration, List<ISpell> removedSpells);

  boolean isSpellAllowed(SpellModel configuration, ISpell spell);

  ISpell[] getLearnedSpells(SpellModel configuration);

  boolean isLearned(SpellModel configuration, ISpell spell);
}