package net.sf.anathema.character.main.testing.dummy.magic;

import net.sf.anathema.character.main.magic.model.spells.CircleType;
import net.sf.anathema.character.main.magic.model.spells.ISpell;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.InitializationContext;
import net.sf.anathema.hero.spells.model.SpellModel;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.util.Identifier;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DummySpellModel implements SpellModel {

  private List<ISpell> spells = new ArrayList<>();

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(InitializationContext context, Hero hero) {
    // nothing to do
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do
  }

  @Override
  public void removeSpells(List<ISpell> removedSpells) {
    throw new NotYetImplementedException();
  }

  @Override
  public void addSpells(List<ISpell> addedSpells) {
    spells.addAll(addedSpells);
  }

  @Override
  public ISpell[] getLearnedSpells() {
    throw new NotYetImplementedException();
  }

  @Override
  public void addChangeListener(ChangeListener listener) {
    throw new NotYetImplementedException();
  }

  @Override
  public boolean isSpellAllowed(ISpell spell) {
    throw new NotYetImplementedException();
  }

  @Override
  public ISpell[] getSpellsByCircle(CircleType circle) {
    throw new NotYetImplementedException();
  }

  @Override
  public ISpell getSpellById(String string) {
    throw new NotYetImplementedException();
  }

  @Override
  public boolean isLearnedOnCreation(ISpell spell) {
    throw new NotYetImplementedException();
  }

  @Override
  public ISpell[] getLearnedSpells(boolean experienced) {
    if (experienced) {
      throw new IllegalArgumentException("Not implemented");
    }
    return spells.toArray(new ISpell[spells.size()]);
  }

  @Override
  public void addSpells(List<ISpell> addedSpells, boolean experienced) {
    if (experienced) {
      throw new IllegalArgumentException("Not implemented");
    }
    spells.addAll(addedSpells);
  }

  @Override
  public void removeSpells(List<ISpell> removedSpells, boolean experienced) {
    if (experienced) {
      throw new IllegalArgumentException("Not implemented");
    }
    spells.removeAll(removedSpells);
  }

  @Override
  public boolean isSpellAllowed(ISpell spell, boolean experienced) {
    throw new NotYetImplementedException();
  }

  @Override
  public boolean isLearned(ISpell spell) {
    return false;
  }

  @Override
  public boolean isLearnedOnCreationOrExperience(ISpell spell) {
    return false;
  }

  @Override
  public List<ISpell> getAvailableSpellsInCircle(CircleType circle) {
    List<ISpell> showSpells = new ArrayList<>();
    Collections.addAll(showSpells, getSpellsByCircle(circle));
    showSpells.removeAll(Arrays.asList(getLearnedSpells()));
    return showSpells;
  }

  @Override
  public List<ISpell> getLearnedSpellsInCircles(CircleType[] eligibleCircles) {
    List<ISpell> spellList = new ArrayList<>();
    for (ISpell spell : getLearnedSpells()) {
      if (ArrayUtils.contains(eligibleCircles, spell.getCircleType())) {
        spellList.add(spell);
      }
    }
    return spellList;
  }
}