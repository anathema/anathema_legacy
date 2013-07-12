package net.sf.anathema.hero.magic.testing;

import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.character.main.magic.model.spells.CircleType;
import net.sf.anathema.character.main.magic.model.spells.Spell;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.magic.advance.creation.MagicLearner;
import net.sf.anathema.hero.magic.model.MagicModel;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.InitializationContext;
import net.sf.anathema.hero.spells.model.SpellsModel;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.util.Identifier;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class DummySpellsModel implements SpellsModel {

  private List<Spell> spells = new ArrayList<>();

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
  public void removeSpells(List<Spell> removedSpells) {
    throw new NotYetImplementedException();
  }

  @Override
  public void addSpells(List<Spell> addedSpells) {
    spells.addAll(addedSpells);
  }

  @Override
  public Spell[] getLearnedSpells() {
    throw new NotYetImplementedException();
  }

  @Override
  public void addChangeListener(ChangeListener listener) {
    throw new NotYetImplementedException();
  }

  @Override
  public boolean isSpellAllowed(Spell spell) {
    throw new NotYetImplementedException();
  }

  @Override
  public Spell[] getSpellsByCircle(CircleType circle) {
    throw new NotYetImplementedException();
  }

  @Override
  public Spell getSpellById(String string) {
    throw new NotYetImplementedException();
  }

  @Override
  public boolean isLearnedOnCreation(Spell spell) {
    throw new NotYetImplementedException();
  }

  @Override
  public Spell[] getLearnedSpells(boolean experienced) {
    if (experienced) {
      throw new IllegalArgumentException("Not implemented");
    }
    return spells.toArray(new Spell[spells.size()]);
  }

  @Override
  public void addSpells(List<Spell> addedSpells, boolean experienced) {
    if (experienced) {
      throw new IllegalArgumentException("Not implemented");
    }
    spells.addAll(addedSpells);
  }

  @Override
  public void removeSpells(List<Spell> removedSpells, boolean experienced) {
    if (experienced) {
      throw new IllegalArgumentException("Not implemented");
    }
    spells.removeAll(removedSpells);
  }

  @Override
  public boolean isSpellAllowed(Spell spell, boolean experienced) {
    throw new NotYetImplementedException();
  }

  @Override
  public boolean isLearned(Spell spell) {
    return false;
  }

  @Override
  public boolean isLearnedOnCreationOrExperience(Spell spell) {
    return false;
  }

  @Override
  public List<Spell> getAvailableSpellsInCircle(CircleType circle) {
    List<Spell> showSpells = new ArrayList<>();
    Collections.addAll(showSpells, getSpellsByCircle(circle));
    showSpells.removeAll(Arrays.asList(getLearnedSpells()));
    return showSpells;
  }

  @Override
  public List<Spell> getLearnedSpellsInCircles(CircleType[] eligibleCircles) {
    List<Spell> spellList = new ArrayList<>();
    for (Spell spell : getLearnedSpells()) {
      if (ArrayUtils.contains(eligibleCircles, spell.getCircleType())) {
        spellList.add(spell);
      }
    }
    return spellList;
  }

  public void initializeMagicModel(MagicModel magicModel) {
    magicModel.addLearnProvider(new MagicLearner() {
      @Override
      public boolean handlesMagic(Magic magic) {
        return magic instanceof Spell;
      }

      @Override
      public int getAdditionalBonusPoints(Magic magic) {
        return 0;
      }

      @Override
      public int getCreationLearnCount(Magic magic, Set<Magic> alreadyHandledMagic) {
        return 1;
      }

      @Override
      public Collection<? extends Magic> getLearnedMagic(boolean experienced) {
        return Arrays.asList(getLearnedSpells(experienced));
      }
    });
  }
}