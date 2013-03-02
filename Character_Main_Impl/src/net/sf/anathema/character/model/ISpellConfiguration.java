package net.sf.anathema.character.model;

import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.lib.control.IChangeListener;

public interface ISpellConfiguration {

  void removeSpells(ISpell[] removedSpells);

  void addSpells(ISpell[] addedSpells);

  ISpell[] getLearnedSpells();

  void addChangeListener(IChangeListener listener);

  void addMagicLearnListener(IMagicLearnListener<ISpell> listener);

  boolean isSpellAllowed(ISpell spell);

  ISpell[] getSpellsByCircle(CircleType circle);

  ISpell getSpellById(String string);

  boolean isLearnedOnCreation(ISpell spell);

  ISpell[] getLearnedSpells(boolean experienced);

  void addSpells(ISpell[] addedSpells, boolean experienced);

  void removeSpells(ISpell[] removedSpells, boolean experienced);

  boolean isSpellAllowed(ISpell spell, boolean experienced);

  boolean isLearned(ISpell spell);

  boolean isLearnedOnCreationOrExperience(ISpell spell);
}