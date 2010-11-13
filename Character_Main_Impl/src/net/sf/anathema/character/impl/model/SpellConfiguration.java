package net.sf.anathema.character.impl.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.generic.impl.magic.SpellException;
import net.sf.anathema.character.generic.impl.magic.persistence.SpellBuilder;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.magic.spells.ICircleTypeVisitor;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.model.IMagicLearnListener;
import net.sf.anathema.character.model.ISpellConfiguration;
import net.sf.anathema.character.model.ISpellLearnStrategy;
import net.sf.anathema.character.model.ISpellMapper;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class SpellConfiguration implements ISpellConfiguration {

  private final List<ISpell> creationLearnedList = new ArrayList<ISpell>();
  private final List<ISpell> experiencedLearnedList = new ArrayList<ISpell>();
  private final ChangeControl changeControl = new ChangeControl();
  private final GenericControl<IMagicLearnListener<ISpell>> magicLearnControl = new GenericControl<IMagicLearnListener<ISpell>>();
  private final Map<CircleType, List<ISpell>> spellsByCircle = new HashMap<CircleType, List<ISpell>>();
  private final List<ISpell> spellsOtherEdition = new ArrayList<ISpell>();
  private final ICharmConfiguration charms;
  private final ISpellLearnStrategy strategy;
  private final ICharacterType characterType;
  private final IExaltedEdition edition;
  private final ISpellMapper spellMapper;

  public SpellConfiguration(
      ICharmConfiguration charms,
      ISpellLearnStrategy strategy,
      ICharacterType characterType,
      IExaltedEdition edition) throws SpellException {
    this.charms = charms;
    this.strategy = strategy;
    this.characterType = characterType;
    this.edition = edition;
    for (CircleType type : CircleType.values()) {
      spellsByCircle.put(type, new ArrayList<ISpell>());
    }
    for (ISpell spell : SpellBuilder.getInstance().getSpells()) {
      if (isEdition(spell)) {
        spellsByCircle.get(spell.getCircleType()).add(spell);
      }
      else {
        spellsOtherEdition.add(spell);
      }
    }
    spellMapper = new SpellMapper();
  }

  private boolean isEdition(ISpell spell) {
    return spell.getSource(edition).getEdition() == edition;
  }

  public void removeSpells(ISpell[] removedSpells) {
    strategy.removeSpells(this, removedSpells);
  }

  public void removeSpells(ISpell[] removedSpells, boolean experienced) {
    for (ISpell spell : removedSpells) {
      if (experienced) {
        experiencedLearnedList.remove(spell);
      }
      else {
        creationLearnedList.remove(spell);
      }
    }
    fireSpellsForgottenEvent(removedSpells);
  }

  public void addSpells(ISpell[] addedSpells) {
    strategy.addSpells(this, addedSpells);
  }

  public void addSpells(ISpell[] addedSpells, boolean experienced) {
    for (ISpell spell : addedSpells) {
      if (isSpellAllowed(spell, experienced)) {
        if (experienced) {
          experiencedLearnedList.add(spell);
        }
        else {
          creationLearnedList.add(spell);
        }
      }
      else {
        throw new IllegalArgumentException("Cannot learn Spell: " + spell); //$NON-NLS-1$
      }
    }
    fireSpellsAddedEvent(addedSpells);
  }

  private void fireSpellsAddedEvent(final ISpell[] addedSpells) {
    magicLearnControl.forAllDo(new IClosure<IMagicLearnListener<ISpell>>() {
      public void execute(IMagicLearnListener<ISpell> input) {
        input.magicLearned(addedSpells);
      }
    });
    changeControl.fireChangedEvent();
  }

  private void fireSpellsForgottenEvent(final ISpell[] removedSpells) {
    magicLearnControl.forAllDo(new IClosure<IMagicLearnListener<ISpell>>() {
      public void execute(IMagicLearnListener<ISpell> input) {
        input.magicForgotten(removedSpells);
      }
    });
    changeControl.fireChangedEvent();
  }

  public boolean isSpellAllowed(ISpell spell) {
    return strategy.isSpellAllowed(this, spell);
  }

  public boolean isSpellAllowed(ISpell spell, final boolean experienced) {
    if (creationLearnedList.contains(spell) || (experienced && experiencedLearnedList.contains(spell))) {
      return false;
    }
    final boolean[] circleLearned = new boolean[1];
    spell.getCircleType().accept(new ICircleTypeVisitor() {
      public void visitTerrestrial(CircleType type) {
        circleLearned[0] = isCharmLearned(characterType.getId() + ".TerrestrialCircleSorcery", experienced); //$NON-NLS-1$
      }

      public void visitCelestial(CircleType type) {
        circleLearned[0] = isCharmLearned(characterType.getId() + ".CelestialCircleSorcery", experienced); //$NON-NLS-1$
      }

      public void visitSolar(CircleType type) {
        circleLearned[0] = isCharmLearned(characterType.getId() + ".SolarCircleSorcery", experienced); //$NON-NLS-1$
      }

      public void visitShadowland(CircleType type) {
        circleLearned[0] = isCharmLearned(characterType.getId() + ".ShadowlandsCircleNecromancy", experienced); //$NON-NLS-1$        
      }

      public void visitLabyrinth(CircleType type) {
        circleLearned[0] = isCharmLearned(characterType.getId() + ".LabyrinthCircleNecromancy", experienced); //$NON-NLS-1$        
      }

      public void visitVoid(CircleType type) {
        circleLearned[0] = isCharmLearned(characterType.getId() + ".VoidCircleNecromancy", experienced); //$NON-NLS-1$        
      }
    });
    return circleLearned[0];
  }

  private boolean isCharmLearned(String charmId, boolean experienced) {
    ICharm charm = charms.getCharmById(charmId);
    ILearningCharmGroup group = charms.getGroup(charm);
    boolean learned = group.isLearned(charm, false) || (experienced && group.isLearned(charm, true));
    return learned;
  }

  public ISpell[] getLearnedSpells() {
    return strategy.getLearnedSpells(this);
  }

  public ISpell[] getLearnedSpells(boolean experienced) {
    List<ISpell> list = new ArrayList<ISpell>();
    list.addAll(creationLearnedList);
    if (experienced) {
      list.addAll(experiencedLearnedList);
    }
    return list.toArray(new ISpell[list.size()]);
  }

  public void addChangeListener(IChangeListener listener) {
    changeControl.addChangeListener(listener);
  }

  public void addMagicLearnListener(IMagicLearnListener<ISpell> listener) {
    magicLearnControl.addListener(listener);
  }

  public ISpell[] getSpellsByCircle(CircleType circle) {
    List<ISpell> spells = spellsByCircle.get(circle);
    if (spells != null) {
      return spells.toArray(new ISpell[spells.size()]);
    }
    return new ISpell[0];
  }

  public ISpell getSpellById(String id) {
    String correctedId = spellMapper.getId(id, edition);
    for (ISpell spell : getAllSpells()) {
      if (spell.getId().equals(correctedId)) {
        return spell;
      }
    }
    throw new IllegalArgumentException("No Spell for id: " + id); //$NON-NLS-1$
  }

  private Iterable<ISpell> getAllSpells() {
    List<ISpell> allSpells = new ArrayList<ISpell>();
    for (List<ISpell> circleSpells : spellsByCircle.values()) {
      allSpells.addAll(circleSpells);
    }
    // Handle any 1e-only spells learned (before spell list filtering) by 2e characters
    allSpells.addAll(spellsOtherEdition);
    return allSpells;
  }

  public boolean isLearnedOnCreation(ISpell spell) {
    return creationLearnedList.contains(spell);
  }

  public boolean isLearnedOnCreationOrExperience(ISpell spell) {
    return experiencedLearnedList.contains(spell) || isLearnedOnCreation(spell);
  }

  public boolean isLearned(ISpell spell) {
    return strategy.isLearned(this, spell);
  }
}