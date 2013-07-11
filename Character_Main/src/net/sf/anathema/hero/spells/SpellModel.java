package net.sf.anathema.hero.spells;

import net.sf.anathema.character.main.magic.model.spells.CircleType;
import net.sf.anathema.character.main.magic.model.spells.ISpell;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.util.List;

public interface SpellModel extends HeroModel {

  Identifier ID = new SimpleIdentifier("Spells");

  void removeSpells(List<ISpell> removedSpells);

  void addSpells(List<ISpell> addedSpells);

  ISpell[] getLearnedSpells();

  void addChangeListener(ChangeListener listener);

  boolean isSpellAllowed(ISpell spell);

  ISpell[] getSpellsByCircle(CircleType circle);

  ISpell getSpellById(String string);

  boolean isLearnedOnCreation(ISpell spell);

  ISpell[] getLearnedSpells(boolean experienced);

  void addSpells(List<ISpell> addedSpells, boolean experienced);

  void removeSpells(List<ISpell> removedSpells, boolean experienced);

  boolean isSpellAllowed(ISpell spell, boolean experienced);

  boolean isLearned(ISpell spell);

  boolean isLearnedOnCreationOrExperience(ISpell spell);

  List<ISpell> getAvailableSpellsInCircle(CircleType circle);

  List<ISpell> getLearnedSpellsInCircles(CircleType[] eligibleCircles);
}