package net.sf.anathema.hero.spells.model;

import net.sf.anathema.character.main.magic.model.spells.CircleType;
import net.sf.anathema.character.main.magic.model.spells.Spell;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.util.List;

public interface SpellsModel extends HeroModel {

  Identifier ID = new SimpleIdentifier("Spells");

  void removeSpells(List<Spell> removedSpells);

  void addSpells(List<Spell> addedSpells);

  Spell[] getLearnedSpells();

  void addChangeListener(ChangeListener listener);

  boolean isSpellAllowed(Spell spell);

  Spell[] getSpellsByCircle(CircleType circle);

  Spell getSpellById(String string);

  boolean isLearnedOnCreation(Spell spell);

  Spell[] getLearnedSpells(boolean experienced);

  void addSpells(List<Spell> addedSpells, boolean experienced);

  void removeSpells(List<Spell> removedSpells, boolean experienced);

  boolean isSpellAllowed(Spell spell, boolean experienced);

  boolean isLearned(Spell spell);

  boolean isLearnedOnCreationOrExperience(Spell spell);

  List<Spell> getAvailableSpellsInCircle(CircleType circle);

  List<Spell> getLearnedSpellsInCircles(CircleType[] eligibleCircles);
}